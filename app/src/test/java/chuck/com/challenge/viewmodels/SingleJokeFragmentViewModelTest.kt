package chuck.com.challenge.viewmodels

import androidx.lifecycle.Observer
import chuck.com.challenge.data.models.Joke
import chuck.com.challenge.data.repositories.JokesRepository
import chuck.com.challenge.data.wrappers.DataState
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.clearInvocations
import com.nhaarman.mockitokotlin2.refEq
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Rule

private const val JOKE_STRING = "Joke 1"
private const val NEW_JOKE_STRING = "Joke 2"
private const val ERROR = "error"

class SingleJokeFragmentViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: SingleJokeFragmentViewModel

    private val joke = mock<Joke> {
        on { value } doReturn JOKE_STRING
    }

    private val jokesRepository = mock<JokesRepository> {
        on { getRandomJoke() } doReturn Single.just(joke)
    }

    private val observer = mock<Observer<DataState<String>>>()

    @Before
    fun setUp() {
        viewModel = SingleJokeFragmentViewModel(jokesRepository)
        viewModel.getJokeLiveData().observeForever(observer)
    }

    @Test
    fun preLoadedWithJoke() {
        verify(jokesRepository).getRandomJoke()
        verify(observer).onChanged(refEq(DataState.Success(JOKE_STRING)))
        verifyNoMoreInteractions(observer, jokesRepository)
    }

    @Test
    fun getLiveDataWithJoke() {
        clearInvocations(jokesRepository, observer)
        whenever(joke.value).thenReturn(NEW_JOKE_STRING)
        viewModel.getNewJoke()

        verify(jokesRepository).getRandomJoke()
        verify(observer).onChanged(refEq(DataState.Loading()))
        verify(observer).onChanged(refEq(DataState.Success(NEW_JOKE_STRING)))
        verifyNoMoreInteractions(observer, jokesRepository)
    }

    @Test
    fun getNewJokeLoadError() {
        clearInvocations(jokesRepository, observer)
        whenever(jokesRepository.getRandomJoke()).thenReturn(Single.error(Throwable(message = ERROR)))
        viewModel.getNewJoke()

        verify(jokesRepository).getRandomJoke()
        verify(observer).onChanged(refEq(DataState.Loading()))
        verify(observer).onChanged(refEq(DataState.Error(ERROR)))
        verifyNoMoreInteractions(observer, jokesRepository)
    }
}