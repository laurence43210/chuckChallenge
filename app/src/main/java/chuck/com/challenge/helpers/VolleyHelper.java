package chuck.com.challenge.helpers;

import android.content.ContentValues;
import chuck.com.challenge.Constants;
import chuck.com.challenge.Enums.ContentValuesEnum;
import chuck.com.challenge.Enums.ServerCallEnum;

/**
 * Created by Laurence on 17/09/2016.
 */
public class VolleyHelper {


    private static String urlMessageFactory(ServerCallEnum serverCallEnum,
                                            ContentValues contentValues) {

        StringBuilder sb = new StringBuilder();

        sb.append(Constants.API_BASE_LINK);

        switch (serverCallEnum) {
            case RANDOM:
                if (contentValues.containsKey(ContentValuesEnum.JOKES_TO_RETRIEVE
                        .getKey())) {
                    sb.append("/");
                    sb.append(contentValues.get(ContentValuesEnum.JOKES_TO_RETRIEVE
                            .getKey()));
                }
                break;
            case NAME_REPLACE:
                if (contentValues.containsKey(ContentValuesEnum.FIRST_NAME.getKey())
                        && contentValues.containsKey(ContentValuesEnum.LAST_NAME
                        .getKey())) {
                    sb.append("/");
                    sb.append(Constants.SINGLE_JOKE_QUANTITY);
                    sb.append("?firstName=");
                    sb.append(contentValues.get(ContentValuesEnum.FIRST_NAME.getKey()));
                    sb.append("&lastName=");
                    sb.append(contentValues.get(ContentValuesEnum.LAST_NAME.getKey()));
                }
                break;
        }

        if (contentValues.containsKey(ContentValuesEnum.RESTRICT_EXPLICIT
                .getKey())
                && contentValues.getAsBoolean(ContentValuesEnum.RESTRICT_EXPLICIT
                .getKey())) {
            sb.append("?");
            sb.append("exclude=[explicit]");

        }

        return sb.toString();
    }


}
