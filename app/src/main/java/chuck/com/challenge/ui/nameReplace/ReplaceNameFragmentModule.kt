package chuck.com.challenge.ui.nameReplace

import chuck.com.challenge.data.repositories.JokesRepository
import chuck.com.challenge.presenters.ReplaceNamePresenter
import chuck.com.challenge.helpers.ResourceHelper
import dagger.Module
import dagger.Provides

@Module
class ReplaceNameFragmentModule {

    @Provides
    fun providePresenter(jokesRepository: JokesRepository, resourceHelper: ResourceHelper) = ReplaceNamePresenter(jokesRepository, resourceHelper)
}