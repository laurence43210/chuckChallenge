package chuck.com.challenge.contracts.singleJoke

import chuck.com.challenge.contracts.MvpView

interface SingleJokeFragmentContract {

    interface View : MvpView {

        fun showJoke(joke: String)

        fun showJokeWithAnimation(joke: String)

        fun onError(message: String)
    }
}