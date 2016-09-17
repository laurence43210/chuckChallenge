package chuck.com.challenge.helpers;

import android.os.Build;
import android.text.Html;
import android.text.TextUtils;

/**
 * Created by Laurence on 17/09/2016.
 */
public class UIHelper {

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

    private static boolean isStringEmptyOrNull(String string) {
        return string == null || TextUtils.isEmpty(string);
    }

}
