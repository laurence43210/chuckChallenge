package chuck.com.challenge.appListeners;

import android.text.SpannableString;

import java.util.List;

import chuck.com.challenge.responsePojo.JokeEntry;

/**
 * Created by Laurence on 12/10/2016.
 */
public interface IBatchJokeView {

    void onJokesLoaded(List<JokeEntry> jokeEntries);

    void onError(String message);

}
