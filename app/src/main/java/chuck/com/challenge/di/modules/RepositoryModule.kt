package chuck.com.challenge.di.modules

import chuck.com.challenge.Models.AsyncJokeRetriever
import chuck.com.challenge.appListeners.ChuckNorrisAPI
import chuck.com.challenge.helpers.SharedPreferencesHelper
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun jokeRepository(chuckNorrisAPI: ChuckNorrisAPI, sharedPreferencesHelper: SharedPreferencesHelper) = AsyncJokeRetriever(chuckNorrisAPI, sharedPreferencesHelper)
}