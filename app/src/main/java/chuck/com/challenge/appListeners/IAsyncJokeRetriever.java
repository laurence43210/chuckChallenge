package chuck.com.challenge.appListeners;

/**
 * Created by Laurence on 12/10/2016.
 */
public interface IAsyncJokeRetriever {

    void RetrieveRandomJoke(OnJokeRetrievedListener onJokeRetrievedListener);

    void RetrieveNameReplaceJoke(OnJokeRetrievedListener onJokeRetrievedListener, String firstName, String lastName);

    void RetrieveBatchJokes(OnJokeRetrievedListener onJokeRetrievedListener);
}
