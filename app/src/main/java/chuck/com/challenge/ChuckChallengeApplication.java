package chuck.com.challenge;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by Laurence on 17/09/2016.
 */
public class ChuckChallengeApplication extends Application {

    private static final String SHARED_PREF_NAME = "ChuckChallengeApplicationPrefs";

    static ChuckChallengeApplication sInstance;

    static SharedPreferences sSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sSharedPreferences = getSharedPreferences(SHARED_PREF_NAME,
                MODE_PRIVATE);
    }

    public static ChuckChallengeApplication getInstance() {
        return sInstance;
    }

    public SharedPreferences getSharedPrefs() {
        return sSharedPreferences;
    }
}
