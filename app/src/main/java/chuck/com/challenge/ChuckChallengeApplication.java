package chuck.com.challenge;

import android.app.Application;

import chuck.com.challenge.DependencyModules.AppModule;
import chuck.com.challenge.DependencyModules.HelperModule;
import chuck.com.challenge.DependencyModules.NetModule;
import chuck.com.challenge.appListeners.DaggerDiComponent;
import chuck.com.challenge.appListeners.DiComponent;

/**
 * Created by Laurence on 17/09/2016.
 */

public class ChuckChallengeApplication extends Application {

    private static DiComponent diComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        diComponent = DaggerDiComponent.builder()
                .appModule(new AppModule(this))
                .helperModule(new HelperModule())
                .netModule(new NetModule(Constants.API_BASE_URL)).build();

    }

    public static DiComponent getDiComponent() {
        return diComponent;
    }
}
