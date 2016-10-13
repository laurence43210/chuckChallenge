package chuck.com.challenge.appListeners;

/**
 * Created by Laurence on 17/09/2016.
 */
public interface GlobalListener {

    void goToActivity(Class clazz);

    void showProgressSpinner();

    void hideProgressSpinner();
}
