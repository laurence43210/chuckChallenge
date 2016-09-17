package chuck.com.challenge;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by Laurence on 17/09/2016.
 */
public class ChuckChallengeApplication extends Application {

    private static final String SHARED_PREF_NAME = "ChuckChallengeApplicationPrefs";

    static ChuckChallengeApplication sInstance;

    static SharedPreferences sSharedPreferences;

    static RequestQueue sRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sRequestQueue = Volley.newRequestQueue(sInstance);
        sSharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
    }

    public static ChuckChallengeApplication getInstance() {
        return sInstance;
    }

    public RequestQueue getVolleyRequestQueue() {
        return sRequestQueue;
    }

    public SharedPreferences getSharedPrefs() {
        return sSharedPreferences;
    }
}
