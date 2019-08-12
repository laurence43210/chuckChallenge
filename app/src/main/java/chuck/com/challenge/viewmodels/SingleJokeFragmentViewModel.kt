package chuck.com.challenge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import chuck.com.challenge.data.repositories.JokesRepository
import chuck.com.challenge.data.wrappers.DataState
import javax.inject.Inject

class SingleJokeFragmentViewModel @Inject constructor(private val jokesRepository: JokesRepository) : BaseViewModel() {

    private val jokeLiveData by lazy {
        val liveData = MutableLiveData<DataState<String>>()
        fetchSingleRandomJoke(liveData)
        return@lazy liveData
    }

    fun getJokeLiveData(): LiveData<DataState<String>> = jokeLiveData

    fun getNewJoke() = fetchSingleRandomJoke(jokeLiveData)

    private fun fetchSingleRandomJoke(liveData: MutableLiveData<DataState<String>>) =
            addDisposable(jokesRepository.getRandomJoke()
                    .doOnSubscribe {
                        liveData.value = DataState.Loading()
                    }
                    .subscribe({
                        val joke = it.value
                        liveData.value = DataState.Success(joke)
                    }, {
                        it.message?.let { message ->
                            liveData.value = DataState.Error(message)
                        }
                    }))
}