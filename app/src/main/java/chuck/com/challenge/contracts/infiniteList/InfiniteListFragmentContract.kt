package chuck.com.challenge.contracts.infiniteList

import chuck.com.challenge.contracts.MvpView
import chuck.com.challenge.data.models.Joke

interface InfiniteListFragmentContract {

    interface View : MvpView {
        fun onJokesLoaded(jokeEntries: List<Joke>)

        fun onError(message: String)
    }
}