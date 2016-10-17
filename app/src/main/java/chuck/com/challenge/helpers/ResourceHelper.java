package chuck.com.challenge.helpers;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 * Created by Laurence on 17/09/2016.

 * Standard helper class for obtaining system resources. See Javadocs of returned methods for further info.
 */
public class ResourceHelper {

    Application application;

    Resources resources;

    public ResourceHelper(Application application, Resources resources) {
        this.application = application;
        this.resources = resources;
    }

    public String getString(int id) {
        return resources.getString(id);
    }

    public Drawable getDrawable(int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return resources.getDrawable(id, application.getTheme());
        } else {
            return resources.getDrawable(id);
        }
    }
}
