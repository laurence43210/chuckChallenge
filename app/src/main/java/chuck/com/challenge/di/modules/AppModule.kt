package chuck.com.challenge.di.modules

import android.app.Application
import android.content.Context.MODE_PRIVATE
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val SHARED_PREF_NAME = "ChuckChallengeApplicationPrefs"

@Module
class AppModule(private val application: Application) {

    @Provides
    fun getApplication() = application

    @Provides
    @Singleton
    internal fun getSharedPreferences(application: Application) = application.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)

    @Provides
    internal fun getResources(application: Application) = application.resources
}