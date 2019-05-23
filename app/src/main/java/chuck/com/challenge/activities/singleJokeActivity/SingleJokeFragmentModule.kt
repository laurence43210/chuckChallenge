package chuck.com.challenge.activities.singleJokeActivity

import chuck.com.challenge.Models.AsyncJokeRetriever
import chuck.com.challenge.Presenters.SingleJokePresenter
import chuck.com.challenge.helpers.ResourceHelper
import chuck.com.challenge.helpers.UIHelper
import dagger.Module
import dagger.Provides

@Module
class SingleJokeFragmentModule {

    @Provides
    fun providePresenter(fragment: SingleJokeFragment, asyncJokeRetriever: AsyncJokeRetriever, resourceHelper: ResourceHelper, uiHelper: UIHelper) = SingleJokePresenter(fragment, asyncJokeRetriever, uiHelper, resourceHelper)
}