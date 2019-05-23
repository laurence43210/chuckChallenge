package chuck.com.challenge.ui.infiniteList

import android.content.res.Resources
import chuck.com.challenge.adapters.JokeListAdapter
import chuck.com.challenge.data.repositories.JokesRepository
import chuck.com.challenge.helpers.UIHelper
import chuck.com.challenge.presenters.BatchJokePresenter
import dagger.Module
import dagger.Provides

@Module
class InfiniteListFragmentModule {

    @Provides
    fun providePresenter(repository: JokesRepository) = BatchJokePresenter(repository)

    @Provides
    fun provideAdapter(fragment: InfiniteListFragment, resources: Resources, uiHelper: UIHelper) = JokeListAdapter(fragment.requireContext(), resources, uiHelper)
}