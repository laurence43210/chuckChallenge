package chuck.com.challenge.viewmodels

import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import chuck.com.challenge.data.datasource.JokeDataSourceFactory
import javax.inject.Inject

private const val PAGE_SIZE = 15

class InfiniteListFragmentViewModel @Inject constructor(private val dataSourceFactory: JokeDataSourceFactory) : BaseViewModel() {

    private val config = Config(PAGE_SIZE, enablePlaceholders = false)

    val paginatedJokeListLiveData = LivePagedListBuilder(dataSourceFactory, config).build()

    override fun onCleared() {
        super.onCleared()
        dataSourceFactory.jokeDataSource.clear()
    }
}