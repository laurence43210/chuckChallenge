package chuck.com.challenge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import chuck.com.challenge.data.repositories.JokesRepository
import chuck.com.challenge.data.wrappers.DataState
import javax.inject.Inject

class SingleJokeFragmentViewModel @Inject constructor(private val jokesRepository: JokesRepository) : BaseViewModel() {

    private val jokeLiveData = MutableLiveData<DataState<String>>()

    init {
        fetchSingleRandomJoke()
    }

    fun getJokeLiveData(): LiveData<DataState<String>> = jokeLiveData

    fun getNewJoke() = fetchSingleRandomJoke()

    private fun fetchSingleRandomJoke() =
            addDisposable(jokesRepository.getRandomJoke()
                    .doOnSubscribe {
                        jokeLiveData.value = DataState.Loading()
                    }
                    .subscribe({
                        val joke = it.value
                        jokeLiveData.value = DataState.Success(joke)
                    }, {
                        it.message?.let { message ->
                            jokeLiveData.value = DataState.Error(message)
                        }
                    }))
}