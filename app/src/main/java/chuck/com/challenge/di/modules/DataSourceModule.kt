package chuck.com.challenge.di.modules

import chuck.com.challenge.data.datasource.JokeDataSource
import chuck.com.challenge.data.datasource.JokeDataSourceFactory
import chuck.com.challenge.data.repositories.JokesRepository
import dagger.Module
import dagger.Provides

@Module
object DataSourceModule {

    @JvmStatic
    @Provides
    fun provideJokeDataSource(jokesRepository: JokesRepository) = JokeDataSource(jokesRepository)

    @JvmStatic
    @Provides
    fun provideJokeDataSourceFactory(jokeDataSource: JokeDataSource) = JokeDataSourceFactory(jokeDataSource)
}