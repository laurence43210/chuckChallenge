package chuck.com.challenge.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import chuck.com.challenge.data.models.Joke

class JokeDataSourceFactory(val jokeDataSource: JokeDataSource) : DataSource.Factory<Int, Joke>() {
    private val sourceLiveData = MutableLiveData<JokeDataSource>()

    override fun create(): DataSource<Int, Joke> {
        sourceLiveData.postValue(jokeDataSource)
        return jokeDataSource
    }
}