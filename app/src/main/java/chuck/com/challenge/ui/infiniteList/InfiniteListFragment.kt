package chuck.com.challenge.ui.infiniteList

import javax.inject.Inject

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

import chuck.com.challenge.R
import chuck.com.challenge.data.models.Joke
import chuck.com.challenge.adapters.JokeListAdapter
import chuck.com.challenge.appListeners.InfiniteListListener
import chuck.com.challenge.data.wrappers.DataState
import chuck.com.challenge.helpers.DialogHelper
import chuck.com.challenge.viewmodels.InfiniteListFragmentViewModel
import chuck.com.challenge.viewmodels.ReplaceNameFragmentViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_infinite_list.recyclerView

class InfiniteListFragment : DaggerFragment() {

    @Inject
    lateinit var dialogHelper: DialogHelper

    @Inject
    lateinit var jokeListAdapter: JokeListAdapter

    @Inject
    lateinit var provider: ViewModelProvider.Factory

    private lateinit var viewModel: InfiniteListFragmentViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, provider).get(InfiniteListFragmentViewModel::class.java)
        viewModel.getJokeListLiveData().observe(viewLifecycleOwner, Observer(::updateJokes))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_infinite_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerView.adapter = jokeListAdapter
        recyclerView.addOnScrollListener(object : InfiniteListListener() {
            override fun onLoadMore() {
                viewModel.loadJokes()
            }
        })
        recyclerView.hasFixedSize()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView.adapter = null
    }

    private fun updateJokes(result: DataState<List<Joke>>) {
        when (result) {
            is DataState.Success -> {
                jokeListAdapter.data = result.data
                jokeListAdapter.notifyDataSetChanged()
            }
            is DataState.Error -> dialogHelper.getErrorDialog(requireContext(), result.message).show()
        }
    }
}
