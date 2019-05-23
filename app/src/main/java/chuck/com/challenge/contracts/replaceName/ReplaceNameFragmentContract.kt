package chuck.com.challenge.contracts.replaceName

import android.text.SpannableString
import chuck.com.challenge.contracts.MvpView

interface ReplaceNameFragmentContract {
    interface View : MvpView {

        fun onSubmitButtonBackgroundChange(drawableId: Int)

        fun onRemoveTextInputLayoutErrorState(enabled: Boolean)

        fun onJokeLoaded(title: SpannableString, joke: String)

        fun onError(message: String)

        fun showInvalidNameError()
    }
}