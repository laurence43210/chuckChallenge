package chuck.com.challenge.di.modules

import android.content.SharedPreferences
import android.content.res.Resources
import chuck.com.challenge.ChuckChallengeApplication
import chuck.com.challenge.helpers.DialogHelper
import chuck.com.challenge.helpers.ResourceHelper
import chuck.com.challenge.helpers.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.android.DaggerApplication

@Module
object HelperModule {

    @JvmStatic
    @Provides
    fun getSharedPreferencesHelper(sharedPreferences: SharedPreferences) = SharedPreferencesHelper(sharedPreferences)

    @JvmStatic
    @Provides
    fun getResourceHelper(application: ChuckChallengeApplication, resources: Resources) = ResourceHelper(application, resources)

    @JvmStatic
    @Provides
    fun getDialogHelper(resourceHelper: ResourceHelper) = DialogHelper(resourceHelper)
}