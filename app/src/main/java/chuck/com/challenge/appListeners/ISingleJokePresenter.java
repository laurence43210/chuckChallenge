package chuck.com.challenge.appListeners;

import chuck.com.challenge.exceptions.NonSplittableNameException;

/**
 * Created by Laurence on 12/10/2016.
 */
public interface ISingleJokePresenter {


    void fetchSingleRandomJoke();

    void fetchNameReplaceJoke(String fullName) throws NonSplittableNameException;
}
