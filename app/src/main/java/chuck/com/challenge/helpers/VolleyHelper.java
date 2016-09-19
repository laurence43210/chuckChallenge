package chuck.com.challenge.helpers;

import com.android.volley.Request;
import com.android.volley.Response;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.util.Log;

import chuck.com.challenge.ChuckChallengeApplication;
import chuck.com.challenge.Constants;
import chuck.com.challenge.responsePojo.ResponseParent;
import chuck.com.challenge.connects.GsonRequest;
import chuck.com.challenge.appEnums.ContentValuesEnum;
import chuck.com.challenge.appEnums.ServerCallEnum;

/**
 * Created by Laurence on 17/09/2016.
 */
public class VolleyHelper {

    public static void makeVolleyCall(ServerCallEnum serverCallEnum,
            ContentValues contentValues,
            Response.Listener<ResponseParent> listener,
            Response.ErrorListener errorListener) {
        try {
            GsonRequest gsonRequest = new GsonRequest<>(urlMessageFactory(
                    serverCallEnum, contentValues), ResponseParent.class,
                    listener, errorListener);
            gsonRequest.setTag(serverCallEnum.toString());
            addRequest(gsonRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void addRequest(@NonNull
    final Request<?> request) {
        ChuckChallengeApplication.getInstance().getVolleyRequestQueue()
                .add(request);
    }

    public static void cancelAllRequests(@NonNull
    final String tag) {
        ChuckChallengeApplication.getInstance().getVolleyRequestQueue()
                .cancelAll(tag);
    }

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
            if (contentValues
                    .containsKey(ContentValuesEnum.FIRST_NAME.getKey())
                    && contentValues.containsKey(ContentValuesEnum.LAST_NAME
                            .getKey())) {
                sb.append("/");
                sb.append(Constants.SINGLE_JOKE_QUANTITY);
                sb.append("?firstName=");
                sb.append(contentValues.get(ContentValuesEnum.FIRST_NAME
                        .getKey()));
                sb.append("&lastName=");
                sb.append(contentValues.get(ContentValuesEnum.LAST_NAME
                        .getKey()));
            }
            break;
        }

        if (contentValues.containsKey(ContentValuesEnum.RESTRICT_EXPLICIT
                .getKey())
                && contentValues
                        .getAsBoolean(ContentValuesEnum.RESTRICT_EXPLICIT
                                .getKey())) {

            switch (serverCallEnum) {
            case RANDOM:
                sb.append("?");
                break;
            case NAME_REPLACE:
                sb.append("&");
                break;
            }
            sb.append("exclude=[");
            sb.append(Constants.EXPLICIT);
            sb.append("]");
        }
        Log.d(VolleyHelper.class.getSimpleName(),
                "Making call to " + sb.toString());
        return sb.toString();
    }

}
