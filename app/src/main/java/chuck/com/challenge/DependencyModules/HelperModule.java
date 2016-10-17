package chuck.com.challenge.DependencyModules;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Resources;

import chuck.com.challenge.helpers.DialogHelper;
import chuck.com.challenge.helpers.RegexHelper;
import chuck.com.challenge.helpers.ResourceHelper;
import chuck.com.challenge.helpers.SharedPreferencesHelper;
import chuck.com.challenge.helpers.UIHelper;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Laurence on 17/10/2016.
 */

@Module
public class HelperModule {

    public HelperModule() {
    }

    @Provides
    SharedPreferencesHelper getSharedPreferencesHelper(
            SharedPreferences sharedPreferences) {
        return new SharedPreferencesHelper(sharedPreferences);
    }

    @Provides
    ResourceHelper getResourceHelper(Application application,
            Resources resources) {
        return new ResourceHelper(application, resources);
    }

    @Provides
    DialogHelper getDialogHelper(ResourceHelper resourceHelper) {
        return new DialogHelper(resourceHelper);
    }

    @Provides
    UIHelper getUIHelper() {
        return new UIHelper();
    }

    @Provides
    RegexHelper getRegexHelper(UIHelper uiHelper) {
        return new RegexHelper(uiHelper);
    }
}