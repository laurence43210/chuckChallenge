package chuck.com.challenge.appListeners;

import java.util.List;

import chuck.com.challenge.responsePojo.JokeEntry;

/**
 * Created by Laurence on 12/10/2016.
 */
public interface OnJokeRetrievedListener {


    void onSuccessBatch(List<JokeEntry> batch);

    void onSuccessSingle(JokeEntry joke);

    void onFail();

}
