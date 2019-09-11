package chuck.com.challenge.ui.infiniteList

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import chuck.com.challenge.data.models.Joke
import chuck.com.challenge.ui.adapters.JokeListAdapter
import dagger.Module
import dagger.Provides

@Module
object InfiniteListFragmentModule {

    @JvmStatic
    @Provides
    fun provideAdapter(fragment: InfiniteListFragment, diffUtil: DiffUtil.ItemCallback<Joke>) = JokeListAdapter(fragment.requireContext(), diffUtil)

    @JvmStatic
    @Provides
    fun providesDiffUtilItemCallback() = object : DiffUtil.ItemCallback<Joke>() {
        override fun areItemsTheSame(oldItem: Joke, newItem: Joke) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Joke, newItem: Joke) = oldItem == newItem
    }

    @JvmStatic
    @Provides
    fun provideItemDecoration(fragment: InfiniteListFragment) = DividerItemDecoration(fragment.requireContext(), RecyclerView.VERTICAL)
}