package chuck.com.challenge.Models;

import chuck.com.challenge.appListeners.ChuckNorrisAPI;
import chuck.com.challenge.appListeners.IAsyncJokeRetriever;
import chuck.com.challenge.appListeners.OnJokeRetrievedListener;
import chuck.com.challenge.helpers.SharedPreferencesHelper;
import chuck.com.challenge.responsePojo.ResponseParent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static chuck.com.challenge.NetConstants.BATCH_JOKE_QUANTITY;
import static chuck.com.challenge.NetConstants.EXPLICIT;
import static chuck.com.challenge.NetConstants.SINGLE_JOKE_QUANTITY;

/**
 * Created by Laurence on 12/10/2016.
 */
public class AsyncJokeRetriever implements IAsyncJokeRetriever {

    ChuckNorrisAPI chuckNorrisAPI;

    SharedPreferencesHelper sharedPreferencesHelper;

    public AsyncJokeRetriever(ChuckNorrisAPI chuckNorrisAPI, SharedPreferencesHelper sharedPreferencesHelper) {
        this.chuckNorrisAPI = chuckNorrisAPI;
        this.sharedPreferencesHelper = sharedPreferencesHelper;
    }

    @Override
    public void RetrieveRandomJoke(
            final OnJokeRetrievedListener onJokeRetrievedListener) {
        Call<ResponseParent> responseParentCall = chuckNorrisAPI.randomJoke(
                SINGLE_JOKE_QUANTITY, sharedPreferencesHelper
                        .getExplicitState() ? EXPLICIT : null);

        responseParentCall.enqueue(new Callback<ResponseParent>() {
            @Override
            public void onResponse(Call<ResponseParent> call,
                    Response<ResponseParent> response) {
                onJokeRetrievedListener.onSuccessSingle(response.body()
                        .getValues().get(0));
            }

            @Override
            public void onFailure(Call<ResponseParent> call, Throwable t) {
                onJokeRetrievedListener.onFail(t.getMessage());
            }
        });

    }

    @Override
    public void RetrieveNameReplaceJoke(
            final OnJokeRetrievedListener onJokeRetrievedListener,
            String firstName, String lastName) {

        Call<ResponseParent> responseParentCall = chuckNorrisAPI.nameReplace(
                firstName, lastName, sharedPreferencesHelper
                        .getExplicitState() ? EXPLICIT : null);

        responseParentCall.enqueue(new Callback<ResponseParent>() {
            @Override
            public void onResponse(Call<ResponseParent> call,
                    Response<ResponseParent> response) {
                onJokeRetrievedListener.onSuccessSingle(response.body()
                        .getValues().get(0));
            }

            @Override
            public void onFailure(Call<ResponseParent> call, Throwable t) {
                onJokeRetrievedListener.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void RetrieveBatchJokes(
            final OnJokeRetrievedListener onJokeRetrievedListener) {

        Call<ResponseParent> responseParentCall = chuckNorrisAPI.randomJoke(
                BATCH_JOKE_QUANTITY, sharedPreferencesHelper
                        .getExplicitState() ? EXPLICIT : null);

        responseParentCall.enqueue(new Callback<ResponseParent>() {
            @Override
            public void onResponse(Call<ResponseParent> call,
                    Response<ResponseParent> response) {
                onJokeRetrievedListener.onSuccessBatch(response.body()
                        .getValues());
            }

            @Override
            public void onFailure(Call<ResponseParent> call, Throwable t) {
                onJokeRetrievedListener.onFail(t.getMessage());
            }
        });

    }

}
