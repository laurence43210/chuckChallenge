package chuck.com.challenge.data.repositories

import chuck.com.challenge.helpers.SharedPreferencesHelper

import chuck.com.challenge.NetConstants.DEFAULT_JOKE_QUANTITY
import chuck.com.challenge.NetConstants.EXCLUDE
import chuck.com.challenge.NetConstants.EXPLICIT
import chuck.com.challenge.NetConstants.FIRST_NAME
import chuck.com.challenge.NetConstants.LAST_NAME
import chuck.com.challenge.data.models.Joke
import chuck.com.challenge.data.remote.JokesRemoteDataSource
import io.reactivex.Single

class JokesRepository(private val jokesRemoteDataSource: JokesRemoteDataSource, private val sharedPreferencesHelper: SharedPreferencesHelper) {

    fun getRandomJoke(): Single<Joke> {
        val map = mutableMapOf<String, String>().apply {
            addExcluded(this)
        }
        return jokesRemoteDataSource.getSingleRandomJoke(map)
    }

    fun getNamedReplaceJoke(firstName: String, lastName: String): Single<Joke> {
        val map = mutableMapOf(FIRST_NAME to firstName, LAST_NAME to lastName).apply {
            addExcluded(this)
        }
        return jokesRemoteDataSource.getSingleRandomJoke(map)
    }

    fun getJokes(quantity: Int = DEFAULT_JOKE_QUANTITY): Single<List<Joke>> {
        val map = mutableMapOf<String, String>().apply {
            addExcluded(this)
        }
        return jokesRemoteDataSource.getJokes(quantity, map)
    }

    private fun addExcluded(map: MutableMap<String, String>): MutableMap<String, String> {
        if (sharedPreferencesHelper.explicitState) {
            map[EXCLUDE] = EXPLICIT
        }
        return map
    }
}