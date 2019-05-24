package chuck.com.challenge.ui.singleJoke

import chuck.com.challenge.data.repositories.JokesRepository
import chuck.com.challenge.presenters.SingleJokePresenter
import dagger.Module
import dagger.Provides

@Module
class SingleJokeFragmentModule {

    @Provides
    fun providePresenter(repository: JokesRepository) = SingleJokePresenter(repository)
}