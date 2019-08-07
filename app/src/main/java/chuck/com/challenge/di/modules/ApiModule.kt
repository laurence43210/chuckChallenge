package chuck.com.challenge.di.modules

import chuck.com.challenge.data.remote.network.ChuckNorrisAPI
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

@Module
object ApiModule {

    @Provides
    @Reusable
    @JvmStatic
    fun provideChuckNorrisAPI(retrofit: Retrofit): ChuckNorrisAPI = retrofit.create(ChuckNorrisAPI::class.java)
}