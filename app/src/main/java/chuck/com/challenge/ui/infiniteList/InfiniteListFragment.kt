package chuck.com.challenge.ui.infiniteList

import java.util.ArrayList

import javax.inject.Inject

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import chuck.com.challenge.R
import chuck.com.challenge.contracts.infiniteList.InfiniteListFragmentContract
import chuck.com.challenge.data.models.Joke
import chuck.com.challenge.presenters.BatchJokePresenter
import chuck.com.challenge.adapters.JokeListAdapter
import chuck.com.challenge.appListeners.InfiniteListListener
import chuck.com.challenge.helpers.DialogHelper
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_infinite_list.recyclerView

private const val RECYCLER_VIEW_DATA = "recycler_view_data"
private const val ADAPTER_DATA = "adapter_data"

class InfiniteListFragment : DaggerFragment(), InfiniteListFragmentContract.View {

    @Inject
    lateinit var dialogHelper: DialogHelper

    @Inject
    lateinit var jokeListAdapter: JokeListAdapter

    @Inject
    lateinit var presenter: BatchJokePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_infinite_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        presenter.attachView(this)
        presenter.fetchBatchOfRandomJokes()
    }

    private fun setUpRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerView.adapter = jokeListAdapter
        recyclerView.addOnScrollListener(object : InfiniteListListener() {
            override fun onLoadMore() {
                presenter.fetchBatchOfRandomJokes()
            }
        })
        recyclerView.hasFixedSize()
    }

    override fun onDestroyView() {
        recyclerView.adapter = null
        super.onDestroyView()
        presenter.destroyAllDisposables()
        presenter.detachView()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            if (it.containsKey(ADAPTER_DATA)) {
                it.getParcelableArrayList<Joke>(ADAPTER_DATA)?.let { jokes ->
                    jokeListAdapter.data = jokes.toMutableList()
                    jokeListAdapter.notifyDataSetChanged()
                }
            }
            if (it.containsKey(RECYCLER_VIEW_DATA)) {
                recyclerView.layoutManager?.onRestoreInstanceState(it.getParcelable(RECYCLER_VIEW_DATA))
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        recyclerView.layoutManager?.let { outState.putParcelable(RECYCLER_VIEW_DATA, it.onSaveInstanceState()) }
        outState.putParcelableArrayList(ADAPTER_DATA, ArrayList(jokeListAdapter.data))
    }

    override fun onJokesLoaded(jokeEntries: List<Joke>) {
        jokeListAdapter.data.addAll(jokeEntries)
        jokeListAdapter.notifyDataSetChanged()
    }

    override fun onError(message: String) = dialogHelper.getErrorDialog(requireContext(), message).show()
}
