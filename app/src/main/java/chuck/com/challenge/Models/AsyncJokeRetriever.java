package chuck.com.challenge.Models;

import chuck.com.challenge.Constants;
import chuck.com.challenge.appListeners.ChuckNorrisAPI;
import chuck.com.challenge.appListeners.IAsyncJokeRetriever;
import chuck.com.challenge.appListeners.OnJokeRetrievedListener;
import chuck.com.challenge.helpers.SharedPreferencesHelper;
import chuck.com.challenge.responsePojo.ResponseParent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Laurence on 12/10/2016.
 */
public class AsyncJokeRetriever implements IAsyncJokeRetriever {

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .client(new OkHttpClient.Builder().addInterceptor(
                    new HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build())
            .addConverterFactory(GsonConverterFactory.create()).build();

    private ChuckNorrisAPI chuckNorrisAPI = retrofit
            .create(ChuckNorrisAPI.class);

    @Override
    public void RetrieveRandomJoke(
            final OnJokeRetrievedListener onJokeRetrievedListener) {
        Call<ResponseParent> responseParentCall = chuckNorrisAPI.randomJoke(
                Constants.SINGLE_JOKE_QUANTITY, SharedPreferencesHelper
                        .isNonExplicitsEnabled() ? Constants.EXPLICIT : null);

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
                firstName, lastName, SharedPreferencesHelper
                        .isNonExplicitsEnabled() ? Constants.EXPLICIT : null);

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
                Constants.BATCH_JOKE_QUANTITY, SharedPreferencesHelper
                        .isNonExplicitsEnabled() ? Constants.EXPLICIT : null);

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
