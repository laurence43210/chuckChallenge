package chuck.com.challenge.presenters

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan

import chuck.com.challenge.R
import chuck.com.challenge.contracts.replaceName.ReplaceNameFragmentContract
import chuck.com.challenge.data.repositories.JokesRepository
import chuck.com.challenge.helpers.ResourceHelper
import javax.inject.Inject

private const val SPACE_CHAR = " "
private const val MIN_NAME_LENGTH = 3

class ReplaceNamePresenter @Inject constructor(private val jokesRepository: JokesRepository, private val resourceHelper: ResourceHelper) : BasePresenter<ReplaceNameFragmentContract.View>() {

    private fun checkNameIsValid(name: String) =
            name.isNotBlank() && name.contains(SPACE_CHAR) && name.length >= MIN_NAME_LENGTH

    fun onSubmitClicked() {
        val name = view?.getName()
        if (name != null && checkNameIsValid(name)) {
            val splitNames = name.split(SPACE_CHAR)
            val firstName = splitNames.first()
            val lastName = splitNames.last()

            addDisposable(jokesRepository.getNamedReplaceJoke(firstName, lastName).subscribe({
                val jokeTitle = String.format(resourceHelper.getString(R.string.joke_number), it.id)

                val titleSpan = SpannableString(jokeTitle)
                titleSpan.setSpan(StyleSpan(Typeface.BOLD), jokeTitle.length - it.id.toString().length, jokeTitle.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

                view?.onJokeLoaded(titleSpan, it.value)
            }, {
                it.message?.let { message ->
                    view?.onError(message)
                }
            }))
        } else {
            view?.showInvalidNameError()
        }
    }

    fun updateViewStateForName(name: String) {
        val isValid = checkNameIsValid(name)
        setSubmitButtonStatus(isValid)
        setEditTextErrorStatus(isValid)
    }

    private fun setSubmitButtonStatus(stringIsValid: Boolean) {
        val background = if (stringIsValid)
            R.drawable.button
            else
            R.drawable.button_faded

        view?.onSubmitButtonBackgroundChange(background)
    }

    private fun setEditTextErrorStatus(stringIsValid: Boolean) {
        view?.onRemoveTextInputLayoutErrorState(stringIsValid)
    }
}
