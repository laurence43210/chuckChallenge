package chuck.com.challenge.data.remote

import chuck.com.challenge.data.models.Joke
import chuck.com.challenge.data.remote.network.ChuckNorrisAPI
import io.reactivex.Scheduler
import io.reactivex.Single

class JokesRemoteDataSource(private val chuckNorrisAPI: ChuckNorrisAPI, private val ioScheduler: Scheduler, private val mainThreadScheduler: Scheduler) {

    fun getSingleRandomJoke(queryMap: Map<String, String>): Single<Joke> = chuckNorrisAPI.getSingleJoke(queryMap)
            .map {
                val firstJoke = it.value.firstOrNull()
                firstJoke ?: throw NoSuchElementException()
            }
            .subscribeOn(ioScheduler)
            .observeOn(mainThreadScheduler)

    fun getJokes(quantity: Int, queryMap: Map<String, String>): Single<List<Joke>> = chuckNorrisAPI.getJokes(quantity, queryMap)
            .map {
                it.value
            }
            .subscribeOn(ioScheduler)
            .observeOn(mainThreadScheduler)
}
