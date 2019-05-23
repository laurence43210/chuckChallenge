package chuck.com.challenge.activities.infiniteListActivity

import android.content.res.Resources
import chuck.com.challenge.Models.AsyncJokeRetriever
import chuck.com.challenge.Presenters.BatchJokePresenter
import chuck.com.challenge.adapters.JokeListAdapter
import chuck.com.challenge.helpers.UIHelper
import dagger.Module
import dagger.Provides

@Module
class InfiniteListFragmentModule {

    @Provides
    fun providePresenter(fragment: InfiniteListFragment, asyncJokeRetriever: AsyncJokeRetriever) = BatchJokePresenter(fragment, asyncJokeRetriever)

    @Provides
    fun provideAdapter(fragment: InfiniteListFragment, resources: Resources, uiHelper: UIHelper) = JokeListAdapter(fragment.requireContext(), resources, uiHelper)
}