package chuck.com.challenge.appListeners;

import android.text.SpannableString;

/**
 * Created by Laurence on 13/10/2016.
 */

public interface IReplaceNameView extends ISingleJokeView {

    void onSubmitButtonBackgroundChange(int drawableId);

    void onRemoveTextInputLayoutErrorState(boolean enabled);


}
