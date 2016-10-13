package chuck.com.challenge.appListeners;

import chuck.com.challenge.responsePojo.ResponseParent;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Laurence on 12/10/2016.
 */

public interface ChuckNorrisAPI {

    @GET("{quantity}")
    Call<ResponseParent> randomJoke(@Path("quantity") int jokeQuantity,
            @Query("exclude") String excludeText);

    @GET("1/?")
    Call<ResponseParent> nameReplace(@Query("firstName") String firstName,
            @Query("lastName") String lastName,
            @Query("exclude") String excludeText);

}
