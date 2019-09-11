package chuck.com.challenge.adapters

import android.content.Context
import android.graphics.Typeface
import androidx.recyclerview.widget.RecyclerView
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import chuck.com.challenge.R
import chuck.com.challenge.data.models.Joke

import chuck.com.challenge.extensions.fromHtml
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.adapter_infinite_list.jokeCategories
import kotlinx.android.synthetic.main.adapter_infinite_list.jokeNumber
import kotlinx.android.synthetic.main.adapter_infinite_list.jokeText

class JokeListAdapter(context: Context) : RecyclerView.Adapter<JokeListAdapter.MyViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    var data: List<Joke>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val view = inflater.inflate(R.layout.adapter_infinite_list, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        data?.let {
            val joke = it[position]
            viewHolder.bind(joke)
        }
    }

    override fun getItemCount() = data?.size ?: 0

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
