package chuck.com.challenge.presenters

import chuck.com.challenge.contracts.infiniteList.InfiniteListFragmentContract
import chuck.com.challenge.data.repositories.JokesRepository
import javax.inject.Inject

class BatchJokePresenter @Inject constructor(private val jokesRepository: JokesRepository) : BasePresenter<InfiniteListFragmentContract.View>() {

    fun fetchBatchOfRandomJokes() = addDisposable(jokesRepository.getJokes().subscribe({
        view?.onJokesLoaded(it)
    }, {
        it.message?.let { message ->
            view?.onError(message)
        }
    }))
}
