package chuck.com.challenge.helpers;

import android.content.SharedPreferences;

import chuck.com.challenge.ChuckChallengeApplication;

/**
 * Created by Laurence on 17/09/2016.
 *
 * Standard helper class for setting and getting shared preference data. See
 * Javadocs of returned methods for further info.
 */

public class SharedPreferencesHelper {

    private static String EXPLICITS_KEY = "explicits_key";

    /**
     *looks for the EXPLICITS_KEY in shared preferences and returns the boolean associated.
     *
     * @return the associated boolean, or false by default if unable to obtain value.
     */

    public static boolean isNonExplicitsEnabled() {
        return containsKey(EXPLICITS_KEY)
                && getBooleanItem(EXPLICITS_KEY, false);
    }

    /**
     *sets the value for the EXPLICITS_KEY in shared preferences
     *
     * @param state the value to set the key
     */

    public static void setNonExplicits(boolean state) {
        setBooleanItem(EXPLICITS_KEY, state);
    }


    /**
     *sets the value for a supplied key in shared preferences
     *
     * @param key the tag to use for the supplied variable.
     * @param value the value to set the key
     */

    private static void setBooleanItem(String key, boolean value) {

        SharedPreferences.Editor editor = ChuckChallengeApplication
                .getInstance().getSharedPrefs().edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     *looks for the supplied key in shared preferences and returns the boolean associated.
     *
     * @param key the tag to use for the requested variable.
     * @param defaultVal the default value to return if the key cannot be found.
     * @return the associated boolean, or false by default if unable to obtain value.
     */

    private static boolean getBooleanItem(String key, boolean defaultVal) {

        return ChuckChallengeApplication.getInstance().getSharedPrefs()
                .getBoolean(key, defaultVal);

    }
    /**
     *looks for the supplied key in shared preferences and returns the boolean associated.
     *
     * @param key the tag to use for the requested variable.
     * @return if the shared preferences contains the supplied key
     */
    private static boolean containsKey(String key) {
        return ChuckChallengeApplication.getInstance().getSharedPrefs()
                .contains(key);
    }

}
