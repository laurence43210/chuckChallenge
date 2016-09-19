package chuck.com.challenge.exceptions;

/**
 * Created by Laurence on 19/09/2016.
 *
 * An Exception to be thrown when a name is invalid and cannot be split,
 * this does not contain sufficient groups to be processed for the service.
 *
 * very unlikely to be thrown, but acts as a fail safe.
 */


public class NonSplittableNameException extends Exception {

    public NonSplittableNameException() {
        super();
    }
}
