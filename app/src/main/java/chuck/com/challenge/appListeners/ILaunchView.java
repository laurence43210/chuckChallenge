package chuck.com.challenge.appListeners;

import android.text.SpannableString;

/**
 * Created by Laurence on 12/10/2016.
 */
public interface ILaunchView {

    void onRandomJokeLoaded(SpannableString title, String joke);

    void onProblem(String string);


}
