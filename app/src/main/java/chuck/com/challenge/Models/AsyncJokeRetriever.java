package chuck.com.challenge.Models;

import javax.inject.Inject;

import chuck.com.challenge.ChuckChallengeApplication;
import chuck.com.challenge.Constants;
import chuck.com.challenge.appListeners.ChuckNorrisAPI;
import chuck.com.challenge.appListeners.IAsyncJokeRetriever;
import chuck.com.challenge.appListeners.OnJokeRetrievedListener;
import chuck.com.challenge.helpers.SharedPreferencesHelper;
import chuck.com.challenge.responsePojo.ResponseParent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Laurence on 12/10/2016.
 */
public class AsyncJokeRetriever implements IAsyncJokeRetriever {

    @Inject
    ChuckNorrisAPI chuckNorrisAPI;

    @Inject
    SharedPreferencesHelper sharedPreferencesHelper;

    public AsyncJokeRetriever() {
        ChuckChallengeApplication.getDiComponent().inject(this);
    }

    @Override
    public void RetrieveRandomJoke(
            final OnJokeRetrievedListener onJokeRetrievedListener) {
        Call<ResponseParent> responseParentCall = chuckNorrisAPI.randomJoke(
                Constants.SINGLE_JOKE_QUANTITY, sharedPreferencesHelper
                        .getExplicitState() ? Constants.EXPLICIT : null);

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
                        .getExplicitState() ? Constants.EXPLICIT : null);

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
                Constants.BATCH_JOKE_QUANTITY, sharedPreferencesHelper
                        .getExplicitState() ? Constants.EXPLICIT : null);

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
