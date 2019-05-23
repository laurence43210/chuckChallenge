package chuck.com.challenge.di.modules

import chuck.com.challenge.appListeners.ChuckNorrisAPI
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    @Reusable
    fun provideChuckNorrisAPI(retrofit: Retrofit) = retrofit.create(ChuckNorrisAPI::class.java)
}