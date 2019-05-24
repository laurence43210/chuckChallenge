package chuck.com.challenge.presenters

import chuck.com.challenge.contracts.singleJoke.SingleJokeFragmentContract
import chuck.com.challenge.data.repositories.JokesRepository

class SingleJokePresenter(private val jokesRepository: JokesRepository) : BasePresenter<SingleJokeFragmentContract.View>() {

    fun fetchSingleRandomJoke(animate: Boolean) = addDisposable(jokesRepository.getRandomJoke().subscribe({
        val joke = it.value
        if (animate) {
            view?.showJokeWithAnimation(joke)
        } else {
            view?.showJoke(joke)
        }
    }, {
        it.message?.let { message ->
            view?.onError(message)
        }
    }))
}