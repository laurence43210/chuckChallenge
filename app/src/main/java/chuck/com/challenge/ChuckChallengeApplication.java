package chuck.com.challenge;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;

/**
 * Created by Laurence on 17/09/2016.
 */
public class ChuckChallengeApplication extends Application {

    static ChuckChallengeApplication sInstance;

    static RequestQueue sRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        sRequestQueue = Volley.newRequestQueue(sInstance);
    }

    public static ChuckChallengeApplication getInstance() {
        return sInstance;
    }

    public RequestQueue getVolleyRequestQueue() {
        return sRequestQueue;
    }

}
