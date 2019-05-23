package chuck.com.challenge.di.modules

import android.app.Application
import android.content.SharedPreferences
import android.content.res.Resources
import chuck.com.challenge.helpers.DialogHelper
import chuck.com.challenge.helpers.ResourceHelper
import chuck.com.challenge.helpers.SharedPreferencesHelper
import chuck.com.challenge.helpers.UIHelper
import dagger.Module
import dagger.Provides

@Module
class HelperModule {

    @Provides
    fun getSharedPreferencesHelper(sharedPreferences: SharedPreferences) = SharedPreferencesHelper(sharedPreferences)

    @Provides
    fun getResourceHelper(application: Application, resources: Resources) = ResourceHelper(application, resources)

    @Provides
    fun getDialogHelper(resourceHelper: ResourceHelper) = DialogHelper(resourceHelper)

    @Provides
    fun getUIHelper() = UIHelper()
}