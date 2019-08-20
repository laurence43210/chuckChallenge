package chuck.com.challenge.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import chuck.com.challenge.data.models.Joke
import chuck.com.challenge.data.repositories.JokesRepository
import chuck.com.challenge.data.wrappers.DataState
import com.nhaarman.mockitokotlin2.clearInvocations
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.refEq
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

import org.junit.Rule

private const val ERROR = "error"

class InfiniteListFragmentViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: InfiniteListFragmentViewModel

    private val joke = mock<Joke>()

    private val jokesRepository = mock<JokesRepository> {
        on { getJokes() } doReturn Single.just(listOf(joke))
    }

    private val observer = mock<Observer<DataState<List<Joke>>>>()

    @Before
    fun setUp() {
        viewModel = InfiniteListFragmentViewModel(jokesRepository)
        viewModel.getJokeListLiveData().observeForever(observer)
    }

    @Test
    fun preLoadedWithJokes() {
        verify(jokesRepository).getJokes()
        verify(observer).onChanged(refEq(DataState.Success(listOf(joke))))
        verifyNoMoreInteractions(observer, jokesRepository)
    }

    @Test
    fun getLiveDataWithJokes() {
        clearInvocations(jokesRepository, observer)
        viewModel.loadJokes()

        verify(jokesRepository).getJokes()
        verify(observer).onChanged(refEq(DataState.Success(listOf(joke, joke))))
        verifyNoMoreInteractions(observer, jokesRepository)
    }

    @Test
    fun getNewJokeLoadError() {
        clearInvocations(jokesRepository, observer)
        whenever(jokesRepository.getJokes()).thenReturn(Single.error(Throwable(message = ERROR)))
        viewModel.loadJokes()

        verify(jokesRepository).getJokes()
        verify(observer).onChanged(refEq(DataState.Error(ERROR)))
        verifyNoMoreInteractions(observer, jokesRepository)
    }
}