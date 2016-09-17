package chuck.com.challenge.helpers;

import chuck.com.challenge.ChuckChallengeApplication;

/**
 * Created by Laurence on 17/09/2016.
 */
public class ResourceHelper {

    public static String getString(int id) {

        return ChuckChallengeApplication.getInstance().getResources()
                .getString(id);
    }

}
