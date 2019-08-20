package chuck.com.challenge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import chuck.com.challenge.data.models.Joke
import chuck.com.challenge.data.repositories.JokesRepository
import chuck.com.challenge.data.wrappers.DataState
import javax.inject.Inject

class InfiniteListFragmentViewModel @Inject constructor(private val jokesRepository: JokesRepository) : BaseViewModel() {

    private val jokeList = mutableListOf<Joke>()

    private val jokeListLiveData by lazy {
        val liveData = MutableLiveData<DataState<List<Joke>>>()
        fetchBatchOfRandomJokes(liveData)
        return@lazy liveData
    }

    fun getJokeListLiveData(): LiveData<DataState<List<Joke>>> = jokeListLiveData

    fun loadJokes() = fetchBatchOfRandomJokes(jokeListLiveData)

    private fun fetchBatchOfRandomJokes(liveData: MutableLiveData<DataState<List<Joke>>>) =
            addDisposable(jokesRepository.getJokes()
                    .subscribe({
                        jokeList.addAll(it)
                        liveData.value = DataState.Success(jokeList)
                    }, {
                        it.message?.let { message ->
                            liveData.value = DataState.Error(message)
                        }
                    }))
}