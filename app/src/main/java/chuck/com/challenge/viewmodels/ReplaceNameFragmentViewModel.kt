package chuck.com.challenge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import chuck.com.challenge.data.models.Joke
import chuck.com.challenge.data.repositories.JokesRepository
import chuck.com.challenge.data.wrappers.DataState
import javax.inject.Inject

private const val SPACE_CHAR = " "
private const val MIN_NAME_LENGTH = 3
internal const val INVALID_NAME_ERROR = "invalid_name"

class ReplaceNameFragmentViewModel @Inject constructor(private val jokesRepository: JokesRepository) : BaseViewModel() {

    private val jokeLiveData = MutableLiveData<DataState<Joke>>()

    private val nameValidLiveData = MutableLiveData<Boolean>()

    fun getJokeLiveData(): LiveData<DataState<Joke>> = jokeLiveData

    fun getNameValidLiveData(): LiveData<Boolean> = nameValidLiveData

    fun onSubmitClicked(enteredName: String?) {
        if (!enteredName.isNullOrEmpty() && checkNameIsValid(enteredName)) {
            val splitNames = enteredName.split(SPACE_CHAR)
            val firstName = splitNames.first()
            val lastName = splitNames.last()

            addDisposable(jokesRepository.getNamedReplaceJoke(firstName, lastName)
                    .doOnSubscribe {
                        jokeLiveData.value = DataState.Loading()
                    }
                    .subscribe({
                        jokeLiveData.value = DataState.Success(it)
                    }, {
                        it.message?.let { message ->
                            jokeLiveData.value = DataState.Error(message)
                        }
                    }))
        } else {
            jokeLiveData.value = DataState.Error(INVALID_NAME_ERROR)
        }
    }

    fun updateViewStateForName(name: String) {
        val isValid = checkNameIsValid(name)
        nameValidLiveData.value = isValid
    }

    private fun checkNameIsValid(name: String) =
            name.isNotBlank() && name.contains(SPACE_CHAR) && name.length >= MIN_NAME_LENGTH
}