package chuck.com.challenge.ui.infiniteList

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import chuck.com.challenge.adapters.JokeListAdapter
import chuck.com.challenge.viewmodels.InfiniteListFragmentViewModel
import dagger.Module
import dagger.Provides

@Module
object InfiniteListFragmentModule {

    @JvmStatic
    @Provides
    fun provideAdapter(fragment: InfiniteListFragment) = JokeListAdapter(fragment.requireContext())

    @JvmStatic
    @Provides
    fun provideInfiniteListFragmentViewModel(target: InfiniteListFragment, provider: ViewModelProvider.Factory) =
            ViewModelProviders.of(target, provider).get(InfiniteListFragmentViewModel::class.java)
}