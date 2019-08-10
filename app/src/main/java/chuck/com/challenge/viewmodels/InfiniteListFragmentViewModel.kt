package chuck.com.challenge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import chuck.com.challenge.data.models.Joke
import chuck.com.challenge.data.repositories.JokesRepository
import chuck.com.challenge.data.wrappers.DataState
import javax.inject.Inject

class InfiniteListFragmentViewModel @Inject constructor(private val jokesRepository: JokesRepository) : BaseViewModel() {

    private val jokeList = mutableListOf<Joke>()

    private val jokeListLiveData = MutableLiveData<DataState<List<Joke>>>()

    init {
        fetchBatchOfRandomJokes()
    }

    fun getJokeListLiveData(): LiveData<DataState<List<Joke>>> = jokeListLiveData

    fun loadJokes() = fetchBatchOfRandomJokes()

    private fun fetchBatchOfRandomJokes() =
            addDisposable(jokesRepository.getJokes()
                    .subscribe({
                        jokeList.addAll(it)
                        jokeListLiveData.value = DataState.Success(jokeList)
                    }, {
                        it.message?.let { message ->
                            jokeListLiveData.value = DataState.Error(message)
                        }
                    }))
}