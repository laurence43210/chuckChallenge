package chuck.com.challenge.DependencyModules;

import chuck.com.challenge.appListeners.ChuckNorrisAPI;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Laurence on 14/10/2016.
 */
@Module
public class NetModule {

    private String baseURL;

    public NetModule(String baseURL) {
        this.baseURL = baseURL;
    }

    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {

        return new Retrofit.Builder().baseUrl(baseURL).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    @Provides
    OkHttpClient provideOkHttpClient() {

        return new OkHttpClient.Builder().addInterceptor(
                new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)).build();
    }

    @Provides
    ChuckNorrisAPI provideChuckNorrisAPI(Retrofit retrofit) {
        return retrofit.create(ChuckNorrisAPI.class);
    }

}
