package chuck.com.challenge.di.modules

import chuck.com.challenge.BuildConfig
import chuck.com.challenge.NetConstants.JOKE_API_BASE_URL
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
object NetModule {

    @JvmStatic
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    @JvmStatic
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder().addNetworkInterceptor(loggingInterceptor).build()

    @JvmStatic
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, @Named(NAME_IO_SCHEDULER) scheduler: Scheduler): Retrofit = Retrofit.Builder()
            .baseUrl(JOKE_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(scheduler))
            .build()
}