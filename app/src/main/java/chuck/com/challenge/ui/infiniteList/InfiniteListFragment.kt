package chuck.com.challenge.ui.infiniteList

import javax.inject.Inject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration

import chuck.com.challenge.R
import chuck.com.challenge.ui.adapters.JokeListAdapter
import chuck.com.challenge.viewmodels.InfiniteListFragmentViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_infinite_list.recyclerView

class InfiniteListFragment : DaggerFragment() {

    @Inject
    lateinit var adapter: JokeListAdapter

    @Inject
    lateinit var provider: ViewModelProvider.Factory

    @Inject
    lateinit var itemDecoration: DividerItemDecoration

    private lateinit var viewModel: InfiniteListFragmentViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, provider).get(InfiniteListFragmentViewModel::class.java)
        viewModel.paginatedJokeListLiveData.observe(viewLifecycleOwner, Observer(adapter::submitList))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_infinite_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(itemDecoration)
        recyclerView.hasFixedSize()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView.adapter = null
    }
}
