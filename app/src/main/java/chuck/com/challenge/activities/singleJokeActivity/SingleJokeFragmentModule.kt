package chuck.com.challenge.activities.singleJokeActivity

import chuck.com.challenge.data.repositories.JokesRepository
import chuck.com.challenge.presenters.SingleJokePresenter
import chuck.com.challenge.helpers.ResourceHelper
import chuck.com.challenge.helpers.UIHelper
import dagger.Module
import dagger.Provides

@Module
class SingleJokeFragmentModule {

    @Provides
    fun providePresenter(repository: JokesRepository, resourceHelper: ResourceHelper, uiHelper: UIHelper) = SingleJokePresenter(repository, uiHelper, resourceHelper)
}