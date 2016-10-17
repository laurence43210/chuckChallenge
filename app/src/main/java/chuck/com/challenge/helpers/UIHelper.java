package chuck.com.challenge.helpers;

import android.os.Build;
import android.text.Html;

/**
 * Created by Laurence on 17/09/2016.
 *
 * A helper class containing methods that assist in providing a good UI
 */
public class UIHelper {

    /**
     *  Returns displayable styled text from the provided HTML string.
     *  Useful when the server response sometimes contains html tags
     *
     * @param  string  raw unformatted html string.
     * @return styled text
     */

    public String convertStringFromHtml(String string) {
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


    /**
     Checks if a string is empty or null
     *
     * @param  string  string to check
     * @return if string is empty or null
     */

    public boolean isStringEmptyOrNull(String string) {
        return string == null || string.length() == 0;
    }

}
