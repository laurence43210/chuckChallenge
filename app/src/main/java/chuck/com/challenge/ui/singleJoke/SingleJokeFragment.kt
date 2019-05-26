package chuck.com.challenge.ui.singleJoke

import javax.inject.Inject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import chuck.com.challenge.R
import chuck.com.challenge.contracts.singleJoke.SingleJokeFragmentContract
import chuck.com.challenge.extentions.fromHtml
import chuck.com.challenge.presenters.SingleJokePresenter
import chuck.com.challenge.helpers.DialogHelper
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_single_joke.showRandomJokeFab
import kotlinx.android.synthetic.main.fragment_single_joke.textSwitcher

class SingleJokeFragment : DaggerFragment(), SingleJokeFragmentContract.View {

    companion object {
        fun newInstance() = SingleJokeFragment()
    }

    @Inject
    lateinit var dialogHelper: DialogHelper

    @Inject
    lateinit var presenter: SingleJokePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
            inflater.inflate(R.layout.fragment_single_joke, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        presenter.fetchSingleRandomJoke(false)
        showRandomJokeFab.setOnClickListener { presenter.fetchSingleRandomJoke(true) }

        textSwitcher.setInAnimation(context, R.anim.slide_in_right)
        textSwitcher.setOutAnimation(context, R.anim.slide_out_left)
    }

    override fun onPause() {
        super.onPause()
        presenter.destroyAllDisposables()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun showJoke(joke: String) = textSwitcher.setCurrentText(joke.fromHtml())

    override fun showJokeWithAnimation(joke: String) = textSwitcher.setText(joke.fromHtml())

    override fun onError(message: String) =
            dialogHelper.getErrorDialog(activity, message).show()
}
