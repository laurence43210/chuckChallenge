package chuck.com.challenge.presenters

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan

import chuck.com.challenge.R
import chuck.com.challenge.contracts.singleJoke.SingleJokeFragmentContract
import chuck.com.challenge.data.repositories.JokesRepository
import chuck.com.challenge.helpers.ResourceHelper
import chuck.com.challenge.helpers.UIHelper

class SingleJokePresenter(private val jokesRepository: JokesRepository, private val uiHelper: UIHelper, private val resourceHelper: ResourceHelper) : BasePresenter<SingleJokeFragmentContract.View>() {

    fun fetchSingleRandomJoke() = addDisposable(jokesRepository.getRandomJoke().subscribe({
        val jokeTitle = String.format(
                resourceHelper.getString(R.string.generic_dialog_title),
                it.id.toString())

        val titleSpan = SpannableString(jokeTitle)
        titleSpan.setSpan(StyleSpan(Typeface.BOLD), jokeTitle.length - it.id.toString().length, jokeTitle.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        view?.onJokeLoaded(titleSpan, uiHelper.convertStringFromHtml(it.value))
    }, {
        it.message?.let { message ->
            view?.onError(message)
        }
    }))
}