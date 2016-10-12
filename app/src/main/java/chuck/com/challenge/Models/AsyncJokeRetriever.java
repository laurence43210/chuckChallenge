package chuck.com.challenge.Models;

import chuck.com.challenge.appListeners.IAsyncJokeRetriever;
import chuck.com.challenge.appListeners.OnJokeRetrievedListener;

/**
 * Created by Laurence on 12/10/2016.
 */
public class AsyncJokeRetriever implements IAsyncJokeRetriever {

    @Override
    public void RetrieveRandomJoke(
            OnJokeRetrievedListener onJokeRetrievedListener) {

    }

    @Override
    public void RetrieveNameReplaceJoke(
            OnJokeRetrievedListener onJokeRetrievedListener, String firstName,
            String lastName) {

    }

    @Override
    public void RetrieveBatchJokes(
            OnJokeRetrievedListener onJokeRetrievedListener) {

    }
}
