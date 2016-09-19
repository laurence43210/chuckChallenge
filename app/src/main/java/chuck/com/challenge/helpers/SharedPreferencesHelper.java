package chuck.com.challenge.helpers;

import android.content.SharedPreferences;

import chuck.com.challenge.ChuckChallengeApplication;

/**
 * Created by Laurence on 17/09/2016.
 */
public class SharedPreferencesHelper {

    private static String EXPLICITS_KEY = "explicits_key";

    public static boolean isNonExplicitsEnabled() {
        return containsKey(EXPLICITS_KEY)
                && getBooleanItem(EXPLICITS_KEY, false);
    }

    public static void setNonExplicits(boolean state) {
        setBooleanItem(EXPLICITS_KEY, state);
    }

    private static void setBooleanItem(String key, boolean value) {

        SharedPreferences.Editor editor = ChuckChallengeApplication
                .getInstance().getSharedPrefs().edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    private static boolean getBooleanItem(String key, boolean defaultVal) {

        return ChuckChallengeApplication.getInstance().getSharedPrefs()
                .getBoolean(key, defaultVal);

    }

    private static boolean containsKey(String key) {
        return ChuckChallengeApplication.getInstance().getSharedPrefs()
                .contains(key);
    }

}
