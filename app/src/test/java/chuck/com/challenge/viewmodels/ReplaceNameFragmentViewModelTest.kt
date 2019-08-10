package chuck.com.challenge.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import chuck.com.challenge.data.models.Joke
import chuck.com.challenge.data.repositories.JokesRepository
import chuck.com.challenge.data.wrappers.DataState
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.refEq
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

import org.junit.Rule

private const val INVALID_NAME = "invalid"
private const val VALID_NAME_FIRST = "first"
private const val VALID_NAME_LAST = "last"

private const val VALID_NAME = "$VALID_NAME_FIRST $VALID_NAME_LAST"
private const val ERROR = "error"

class ReplaceNameFragmentViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ReplaceNameFragmentViewModel

    private val joke = mock<Joke>()

    private val jokesRepository = mock<JokesRepository> {
        on { getNamedReplaceJoke(VALID_NAME_FIRST, VALID_NAME_LAST) } doReturn Single.just(joke)
    }

    private val jokeObserver = mock<Observer<DataState<Joke>>>()

    private val nameValidObserver = mock<Observer<Boolean>>()

    @Before
    fun setUp() {
        viewModel = ReplaceNameFragmentViewModel(jokesRepository)
        viewModel.getJokeLiveData().observeForever(jokeObserver)
        viewModel.getNameValidLiveData().observeForever(nameValidObserver)
    }

    @Test
    fun onSubmitClickedInvalidName() {
        viewModel.onSubmitClicked(INVALID_NAME)

        verifyZeroInteractions(jokesRepository)
        verify(jokeObserver).onChanged(refEq(DataState.Error(INVALID_NAME_ERROR)))
        verifyNoMoreInteractions(jokeObserver)
    }

    @Test
    fun onSubmitClickedValidName() {
        viewModel.onSubmitClicked(VALID_NAME)

        verify(jokesRepository).getNamedReplaceJoke(VALID_NAME_FIRST, VALID_NAME_LAST)
        verify(jokeObserver).onChanged(refEq(DataState.Loading()))
        verify(jokeObserver).onChanged(refEq(DataState.Success(joke)))
        verifyNoMoreInteractions(jokeObserver, jokesRepository)
    }

    @Test
    fun onSubmitClickedValidNameError() {
        whenever(jokesRepository.getNamedReplaceJoke(VALID_NAME_FIRST, VALID_NAME_LAST)).thenReturn(Single.error(Throwable(message = ERROR)))
        viewModel.onSubmitClicked(VALID_NAME)

        verify(jokesRepository).getNamedReplaceJoke(VALID_NAME_FIRST, VALID_NAME_LAST)
        verify(jokeObserver).onChanged(refEq(DataState.Loading()))
        verify(jokeObserver).onChanged(refEq(DataState.Error(ERROR)))
        verifyNoMoreInteractions(jokeObserver, jokesRepository)
    }

    @Test
    fun updateViewStateForNameInvalid() {
        viewModel.updateViewStateForName(INVALID_NAME)
        verify(nameValidObserver).onChanged(false)
        verifyNoMoreInteractions(nameValidObserver)
    }

    @Test
    fun updateViewStateForNameValid() {
        viewModel.updateViewStateForName(VALID_NAME)
        verify(nameValidObserver).onChanged(true)
        verifyNoMoreInteractions(nameValidObserver)
    }
}
