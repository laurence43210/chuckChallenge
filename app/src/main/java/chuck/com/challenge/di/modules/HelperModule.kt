package chuck.com.challenge.di.modules

import android.content.SharedPreferences
import chuck.com.challenge.helpers.SharedPreferencesHelper
import dagger.Module
import dagger.Provides

@Module
object HelperModule {

    @JvmStatic
    @Provides
    fun getSharedPreferencesHelper(sharedPreferences: SharedPreferences) = SharedPreferencesHelper(sharedPreferences)
}