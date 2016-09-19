package chuck.com.challenge.helpers;

import com.android.volley.Request;
import com.android.volley.Response;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.util.Log;

import chuck.com.challenge.ChuckChallengeApplication;
import chuck.com.challenge.Constants;
import chuck.com.challenge.appEnums.ContentValuesEnum;
import chuck.com.challenge.appEnums.ServerCallEnum;
import chuck.com.challenge.connects.GsonRequest;
import chuck.com.challenge.responsePojo.ResponseParent;

/**
 * Created by Laurence on 17/09/2016.
 *
 * A helper class construct server requests and hand them to Volley.
 */
public class VolleyHelper {

    /**
     * This publicly callable method will construct a Gson request and then hand over to volley. This method will make use of
     * other private methods in the helper providing required data is provided.
     *
     * Upon passing the request to volley, the rest of the server call is handled
     * automatically, on separate thread to the UI thread. Supplied listeners will be triggered depending on the outcome.
     *
     * @param  serverCallEnum  enum to define the purpose of the server call. This cannot be null.
     * @param contentValues params required for the server call. This cannot be null, and should be
     *                      populated with the data required in the url message factory for the
     *                      particular service call
     * @param  listener listener to be called upon a successful request. this will be passed a parsed
     *                  Response Parent object
     * @param  errorListener listener to be called on an unsuccessful request
     */

    public static void makeVolleyCall(@NonNull ServerCallEnum serverCallEnum,
            @NonNull ContentValues contentValues,
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

    /**
     * Adds a request to Volley's queue, this will automaticly call the request.
     *
     @param request the request to add to the queue.
     */

    private static void addRequest(@NonNull
    final Request<?> request) {
        ChuckChallengeApplication.getInstance().getVolleyRequestQueue()
                .add(request);
    }

    /**
     * Cancels all requests of a certain type, useful if a request is stuck in the queue.
     *
     @param tag The server call enum associated with the request type.
     */

    public static void cancelAllRequests(@NonNull
    final ServerCallEnum tag) {
        ChuckChallengeApplication.getInstance().getVolleyRequestQueue()
                .cancelAll(tag.toString());
    }

    /**
     * Creates the correct url to use for the server request. Requires the content values to contain
     * the following for each server request
     *
     * RANDOM - the amount of jokes to receive in tag JOKES_TO_RETRIEVE
     *
     * NAME_REPLACE - first and last names as strings in tags FIRST_NAME | LAST_NAME
     *
     * @param serverCallEnum The server call enum associated with the request type. This cannot be null
     * @param contentValues params required for the server call. This cannot be null, and should be
     *                      populated with the data required in the url message factory for the
     *                      particular service call
     * @see ContentValuesEnum for the tags to use
     */

    private static String urlMessageFactory(
            @NonNull ServerCallEnum serverCallEnum,
            @NonNull ContentValues contentValues) {

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
