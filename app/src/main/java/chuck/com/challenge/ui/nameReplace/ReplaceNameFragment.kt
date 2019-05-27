package chuck.com.challenge.ui.nameReplace

import javax.inject.Inject

import android.content.Context
import android.os.Bundle
import android.text.Editable

import android.text.SpannableString
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.res.ResourcesCompat

import chuck.com.challenge.R
import chuck.com.challenge.contracts.replaceName.ReplaceNameFragmentContract
import chuck.com.challenge.extentions.fromHtml
import chuck.com.challenge.presenters.ReplaceNamePresenter
import chuck.com.challenge.helpers.DialogHelper
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_name_replace.inputLayoutName
import kotlinx.android.synthetic.main.fragment_name_replace.inputName
import kotlinx.android.synthetic.main.fragment_name_replace.submitButton

class ReplaceNameFragment : DaggerFragment(), ReplaceNameFragmentContract.View, TextWatcher {

    @Inject
    lateinit var dialogHelper: DialogHelper

    @Inject
    lateinit var presenter: ReplaceNamePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_name_replace, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        inputName.addTextChangedListener(this)
        submitButton.setOnClickListener {
            hideKeyboard()
            presenter.onSubmitClicked()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.destroyAllDisposables()
        presenter.detachView()
    }

    override fun onJokeLoaded(title: SpannableString, joke: String) = dialogHelper.getDialogWithOkButton(activity, title, joke.fromHtml()).show()

    override fun onError(message: String) = dialogHelper.getErrorDialog(activity, message).show()

    override fun onSubmitButtonBackgroundChange(drawableId: Int) {
        submitButton.background = ResourcesCompat.getDrawable(resources, drawableId, null)
    }

    override fun onRemoveTextInputLayoutErrorState(enabled: Boolean) {
        if (enabled) {
            inputLayoutName.error = null
        }
    }

    private fun hideKeyboard() {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    override fun showInvalidNameError() {
        inputLayoutName.error = getString(R.string.name_replace_error_message_name)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) = presenter.updateViewStateForName(s.toString())

    override fun afterTextChanged(s: Editable) = Unit

    override fun getName() = inputName.text.toString()
}
