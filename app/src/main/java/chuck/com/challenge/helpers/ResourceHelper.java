package chuck.com.challenge.helpers;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import chuck.com.challenge.ChuckChallengeApplication;

/**
 * Created by Laurence on 17/09/2016.
 */
public class ResourceHelper {

    public static String getString(int id) {

        return ChuckChallengeApplication.getInstance().getResources()
                .getString(id);
    }

    public static int getColor(int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(
                    ChuckChallengeApplication.getInstance(), id);
        } else {
            return ChuckChallengeApplication.getInstance().getResources()
                    .getColor(id);
        }
    }

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
