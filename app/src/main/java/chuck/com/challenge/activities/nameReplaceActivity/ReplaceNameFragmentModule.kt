package chuck.com.challenge.activities.nameReplaceActivity

import chuck.com.challenge.data.repositories.JokesRepository
import chuck.com.challenge.presenters.ReplaceNamePresenter
import chuck.com.challenge.helpers.RegexHelper
import chuck.com.challenge.helpers.ResourceHelper
import chuck.com.challenge.helpers.UIHelper
import dagger.Module
import dagger.Provides

@Module
class ReplaceNameFragmentModule {

    @Provides
    fun providePresenter(jokesRepository: JokesRepository, resourceHelper: ResourceHelper, uiHelper: UIHelper, regexHelper: RegexHelper) = ReplaceNamePresenter(jokesRepository, uiHelper, resourceHelper, regexHelper)

    @Provides
    fun getRegexHelper(uiHelper: UIHelper) = RegexHelper(uiHelper)
}