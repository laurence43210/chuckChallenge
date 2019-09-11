package chuck.com.challenge.ui.nameReplace

import javax.inject.Inject

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable

import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import chuck.com.challenge.R
import chuck.com.challenge.data.models.Joke
import chuck.com.challenge.data.wrappers.DataState
import chuck.com.challenge.extentions.fromHtml
import chuck.com.challenge.helpers.DialogHelper
import chuck.com.challenge.viewmodels.INVALID_NAME_ERROR
import chuck.com.challenge.viewmodels.InfiniteListFragmentViewModel
import chuck.com.challenge.viewmodels.ReplaceNameFragmentViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_name_replace.inputLayoutName
import kotlinx.android.synthetic.main.fragment_name_replace.inputName
import kotlinx.android.synthetic.main.fragment_name_replace.submitButton

class ReplaceNameFragment : DaggerFragment(), TextWatcher {

    @Inject
    lateinit var dialogHelper: DialogHelper

    @Inject
    lateinit var provider: ViewModelProvider.Factory

    private lateinit var viewModel: ReplaceNameFragmentViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, provider).get(ReplaceNameFragmentViewModel::class.java)
        viewModel.getJokeLiveData().observe(viewLifecycleOwner, Observer(::onJokeResult))
        viewModel.getNameValidLiveData().observe(viewLifecycleOwner, Observer(::setViewsForNameState))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_name_replace, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inputName.addTextChangedListener(this)
        submitButton.setOnClickListener {
            hideKeyboard()
            viewModel.onSubmitClicked(inputName.text.toString())
        }
    }

    private fun onJokeResult(result: DataState<Joke>) {
        when (result) {
            is DataState.Success -> result.data?.let { joke ->
                val jokeTitle = getString(R.string.joke_number, joke.id)

                val titleSpan = SpannableString(jokeTitle)
                titleSpan.setSpan(StyleSpan(Typeface.BOLD), jokeTitle.length - joke.id.toString().length, jokeTitle.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

                dialogHelper.getDialogWithOkButton(requireContext(), titleSpan, joke.value.fromHtml()).show()
            }
            is DataState.Error -> {
                val message = result.message
                if (message == INVALID_NAME_ERROR) {
                    inputLayoutName.error = getString(R.string.name_replace_error_message_name)
                } else {
                    dialogHelper.getErrorDialog(requireContext(), message).show()
                }
            }
        }
    }

    private fun hideKeyboard() {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    private fun setViewsForNameState(isValid: Boolean) {
        val drawableId = if (isValid) R.drawable.button else R.drawable.button_faded
        submitButton.background = ResourcesCompat.getDrawable(resources, drawableId, null)

        if (isValid) {
            inputLayoutName.error = null
        }
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = viewModel.updateViewStateForName(s.toString())

    override fun afterTextChanged(s: Editable) = Unit
}
