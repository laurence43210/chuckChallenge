package chuck.com.challenge.ui.adapters

import android.content.Context
import android.graphics.Typeface
import androidx.recyclerview.widget.RecyclerView
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

import chuck.com.challenge.R
import chuck.com.challenge.data.models.Joke

import chuck.com.challenge.extensions.fromHtml
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_joke.jokeCategories
import kotlinx.android.synthetic.main.item_joke.jokeNumber
import kotlinx.android.synthetic.main.item_joke.jokeText

class JokeListAdapter(context: Context, diffUtil: DiffUtil.ItemCallback<Joke>) : PagedListAdapter<Joke, JokeListAdapter.MyViewHolder>(diffUtil) {

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.item_joke, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        getItem(position)?.let {
            viewHolder.bind(it)
        }
    }

    class MyViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(joke: Joke) {
            val (id: Int, value: String, categories: List<String>) = joke

            val jokeTitle = itemView.context.getString(R.string.joke_number, id)

            val spannableString = SpannableString(jokeTitle).apply {
                val titleLength = jokeTitle.length
                setSpan(StyleSpan(Typeface.BOLD), titleLength - id.toString().length, titleLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

            jokeText.text = value.fromHtml()

            jokeNumber.text = spannableString

            if (categories.isNotEmpty()) {
                jokeCategories.visibility = View.VISIBLE
                jokeCategories.text = categories.joinToString()
            } else {
                jokeCategories.visibility = View.GONE
            }
        }
    }
}
