package chuck.com.challenge.data.remote.network

import chuck.com.challenge.NetConstants.SINGLE_JOKE_QUANTITY
import chuck.com.challenge.data.models.Joke
import chuck.com.challenge.data.models.ResponseWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ChuckNorrisAPI {

    @GET("{quantity}")
    fun getJokes(@Path("quantity") jokeQuantity: Int, @QueryMap map: Map<String, String>): Single<ResponseWrapper<Joke>>

    @GET(SINGLE_JOKE_QUANTITY)
    fun getSingleJoke(@QueryMap map: Map<String, String>): Single<ResponseWrapper<Joke>>
}
