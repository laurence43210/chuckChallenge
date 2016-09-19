package chuck.com.challenge.helpers;

import android.os.Build;
import android.text.Html;

/**
 * Created by Laurence on 17/09/2016.
 */
public class UIHelper {

    //The String returned from the service seems to contain html tags for "
    //this method will convert it to ui friendly text.

    public static String convertStringFromHtml(String string) {
        if (!isStringEmptyOrNull(string)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return String.valueOf(Html.fromHtml(string,
                        Html.FROM_HTML_MODE_LEGACY));
            } else {
                return String.valueOf(Html.fromHtml(string));
            }
        }
        return "";
    }

    public static boolean isStringEmptyOrNull(String string) {
        return string == null || string.length() == 0;
    }

}
