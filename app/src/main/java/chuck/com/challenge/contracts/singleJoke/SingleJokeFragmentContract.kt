package chuck.com.challenge.contracts.singleJoke

import android.text.SpannableString
import chuck.com.challenge.contracts.MvpView

interface SingleJokeFragmentContract {

    interface View : MvpView {

        fun onJokeLoaded(title: SpannableString, joke: String)

        fun onError(message: String)
    }
}