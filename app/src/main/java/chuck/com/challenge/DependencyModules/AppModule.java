package chuck.com.challenge.DependencyModules;

import static android.content.Context.MODE_PRIVATE;

import javax.inject.Singleton;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.Resources;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Laurence on 14/10/2016.
 */

@Module
public class AppModule {

    private Application application;

    private static final String SHARED_PREF_NAME = "ChuckChallengeApplicationPrefs";

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application getApplication() {
        return application;
    }

    @Provides
    @Singleton
    SharedPreferences getSharedPreferences(Application application) {
        return application.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    }

    @Provides
    Resources getResources(Application application) {
        return application.getResources();
    }




}
