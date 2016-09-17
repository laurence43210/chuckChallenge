package chuck.com.challenge;

import android.app.Application;


/**
 * Created by Laurence on 17/09/2016.
 */
public class ChuckChallengeApplication extends Application {

    static ChuckChallengeApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static ChuckChallengeApplication getInstance() {
        return sInstance;
    }

}
