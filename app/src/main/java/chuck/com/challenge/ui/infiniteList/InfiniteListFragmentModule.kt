package chuck.com.challenge.ui.infiniteList

import chuck.com.challenge.adapters.JokeListAdapter
import dagger.Module
import dagger.Provides

@Module
class InfiniteListFragmentModule {

    @Provides
    fun provideAdapter(fragment: InfiniteListFragment) = JokeListAdapter(fragment.requireContext())
}