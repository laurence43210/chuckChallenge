package chuck.com.challenge.di.modules

import chuck.com.challenge.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.logging.HttpLoggingInterceptor

@Module
class NetModule {

    @Provides
    @Reusable
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

}