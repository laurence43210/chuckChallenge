package chuck.com.challenge.helpers;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import chuck.com.challenge.ChuckChallengeApplication;

/**
 * Created by Laurence on 17/09/2016.

 * Standard helper class for obtaining system resources. See Javadocs of returned methods for further info.
 */
public class ResourceHelper {

    /**
     * Finds by id and returns string from resources
     *
     * @param id the R address of the string to retrieve
     * @return associated string
     */
    public static String getString(int id) {

        return ChuckChallengeApplication.getInstance().getResources()
                .getString(id);
    }

    /**
     * Finds by id and returns colour from resources
     *
     * @param id the R address of the colour to retrieve
     * @return associated colour
     */
    public static int getColour(int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(
                    ChuckChallengeApplication.getInstance(), id);
        } else {
            return ChuckChallengeApplication.getInstance().getResources()
                    .getColor(id);
        }
    }

    /**
     * Finds by id and returns drawable from resources
     *
     * @param id the R address of the drawable to retrieve
     * @return associated drawable
     */

    public static Drawable getDrawable(int id) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return ChuckChallengeApplication
                    .getInstance()
                    .getResources()
                    .getDrawable(id,
                            ChuckChallengeApplication.getInstance().getTheme());
        } else {
            return ChuckChallengeApplication.getInstance().getResources()
                    .getDrawable(id);
        }
    }
}
