package chuck.com.challenge.appListeners;

import android.text.SpannableString;

/**
 * Created by Laurence on 12/10/2016.
 */
public interface ISingleJokeView {

    void onJokeLoaded(SpannableString title, String joke);

    void onError(String message);

}
