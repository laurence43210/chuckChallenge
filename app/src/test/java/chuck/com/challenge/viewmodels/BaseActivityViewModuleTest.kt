package chuck.com.challenge.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import chuck.com.challenge.R
import com.nhaarman.mockitokotlin2.clearInvocations
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BaseActivityViewModuleTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: BaseActivityViewModule

    private val observer = mock<Observer<Int>>()

    @Before
    fun setUp() {
        viewModel = BaseActivityViewModule()
        viewModel.getSelectedTabId().observeForever(observer)
    }

    @Test
    fun setToRandomJokeByDefault() {
        verify(observer).onChanged(R.id.randomJoke)
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun setToRandomJoke() {
        clearInvocations(observer)
        viewModel.setToRandomJoke()

        verify(observer).onChanged(R.id.randomJoke)
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun setToNameReplace() {
        clearInvocations(observer)
        viewModel.setToNameReplace()

        verify(observer).onChanged(R.id.nameReplace)
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun setToInfiniteList() {
        clearInvocations(observer)
        viewModel.setToInfiniteList()

        verify(observer).onChanged(R.id.infiniteList)
        verifyNoMoreInteractions(observer)
    }
}