package chuck.com.challenge.data.datasource

import androidx.paging.PositionalDataSource
import chuck.com.challenge.data.models.Joke
import chuck.com.challenge.data.repositories.JokesRepository
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class JokeDataSource(private val jokesRepository: JokesRepository) : PositionalDataSource<Joke>() {

    private val compositeDisposable = CompositeDisposable()

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Joke>) {
        compositeDisposable.add(jokesRepository.getJokes(params.loadSize)
                .subscribe({
                    callback.onResult(it)
                }, Timber::e))
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Joke>) {
        compositeDisposable.add(jokesRepository.getJokes(params.requestedLoadSize)
                .subscribe({
                    callback.onResult(it, params.requestedStartPosition, it.size)
                }, Timber::e))
    }

    fun clear() = compositeDisposable.clear()
}