package chuck.com.challenge.ui.infiniteList

import chuck.com.challenge.adapters.JokeListAdapter
import dagger.Module
import dagger.Provides

@Module
object InfiniteListFragmentModule {

    @JvmStatic
    @Provides
    fun provideAdapter(fragment: InfiniteListFragment) = JokeListAdapter(fragment.requireContext())

}