package chuck.com.challenge.di.modules

import android.content.Context.MODE_PRIVATE
import dagger.Module
import dagger.Provides
import dagger.android.DaggerApplication
import javax.inject.Singleton

private const val SHARED_PREF_NAME = "ChuckChallengeApplicationPrefs"

@Module
object AppModule {

    @Provides
    @Singleton
    @JvmStatic
    internal fun getSharedPreferences(application: DaggerApplication) = application.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)

    @Provides
    @JvmStatic
    internal fun getResources(application: DaggerApplication) = application.resources
}