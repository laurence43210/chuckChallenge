package chuck.com.challenge.ui.singleJoke

import javax.inject.Inject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import chuck.com.challenge.R
import chuck.com.challenge.data.wrappers.DataState
import chuck.com.challenge.extentions.fromHtml
import chuck.com.challenge.helpers.DialogHelper
import chuck.com.challenge.viewmodels.ReplaceNameFragmentViewModel
import chuck.com.challenge.viewmodels.SingleJokeFragmentViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_single_joke.showRandomJokeFab
import kotlinx.android.synthetic.main.fragment_single_joke.textSwitcher

class SingleJokeFragment : DaggerFragment() {

    companion object {
        fun newInstance() = SingleJokeFragment()
    }

    @Inject
    lateinit var dialogHelper: DialogHelper

    @Inject
    lateinit var provider: ViewModelProvider.Factory

    private lateinit var viewModel: SingleJokeFragmentViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, provider).get(SingleJokeFragmentViewModel::class.java)
        viewModel.getJokeLiveData().observe(viewLifecycleOwner, Observer(::updateJoke))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
            inflater.inflate(R.layout.fragment_single_joke, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showRandomJokeFab.setOnClickListener { viewModel.getNewJoke() }
        textSwitcher.setInAnimation(context, R.anim.slide_in_right)
        textSwitcher.setOutAnimation(context, R.anim.slide_out_left)
    }

    private fun updateJoke(result: DataState<String>) {
        when (result) {
            is DataState.Success -> {
                textSwitcher.currentView.let { view ->
                    val joke = result.data?.fromHtml()
                    if ((view as TextView).text.isNullOrEmpty()) {
                        textSwitcher.setCurrentText(joke)
                    } else {
                        textSwitcher.setText(joke)
                    }
                }
            }
            is DataState.Error -> dialogHelper.getErrorDialog(requireContext(), result.message).show()
        }
    }
}
