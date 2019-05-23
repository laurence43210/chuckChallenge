package chuck.com.challenge.helpers;


import android.content.SharedPreferences;


/**
 * Created by Laurence on 17/09/2016.
 *
 * Standard helper class for setting and getting shared preference data. See
 * Javadocs of returned methods for further info.
 */

public class SharedPreferencesHelper {

    private static String EXPLICITS_KEY = "explicits_key";

    SharedPreferences sharedPreferences;

    public SharedPreferencesHelper(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }


    public void setExplicits(boolean state) {
        setBooleanItem(EXPLICITS_KEY, state);
    }

    public boolean getExplicitState() {
        return getBooleanItem(EXPLICITS_KEY, false);
    }

    private void setBooleanItem(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    private boolean getBooleanItem(String key, boolean defaultVal) {

        return sharedPreferences.getBoolean(key, defaultVal);

    }

    private boolean containsKey(String key) {
        return sharedPreferences.contains(key);
    }


}
