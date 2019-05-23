package chuck.com.challenge.adapters;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import androidx.recyclerview.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import chuck.com.challenge.R;
import chuck.com.challenge.data.models.Joke;
import chuck.com.challenge.helpers.UIHelper;

import static chuck.com.challenge.NetConstants.EXPLICIT;
import static chuck.com.challenge.NetConstants.NERDY;

/**
 * Created by Laurence on 17/09/2016.
 */
public class JokeListAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Joke> data = new ArrayList<>();

    private LayoutInflater mInflater;

        Resources resources;
        UIHelper uiHelper;

    public JokeListAdapter(Context context, Resources resources, UIHelper uiHelper) {
        mInflater = LayoutInflater.from(context);
        this.resources = resources;
        this.uiHelper = uiHelper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup,
            int viewType) {
        return new MyViewHolder(mInflater.inflate(
                R.layout.adapter_infinite_list, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,
            int position) {

        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        Joke jokeEntry = data.get(position);
        myViewHolder.jokeText.setText(uiHelper.convertStringFromHtml(jokeEntry
                .getValue()));

        String jokeTitle = String.format(
                resources.getString(R.string.generic_dialog_title),
                String.valueOf(jokeEntry.getId()));

        SpannableString spannableString = new SpannableString(jokeTitle);
        spannableString
                .setSpan(new StyleSpan(Typeface.BOLD), jokeTitle.length()
                        - String.valueOf(jokeEntry.getId()).length(),
                        jokeTitle.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        myViewHolder.jokeNumber.setText(spannableString);

        if (jokeEntry.getCategories().contains(EXPLICIT)) {
            myViewHolder.explicitTag.setVisibility(View.VISIBLE);
        }
        if (jokeEntry.getCategories().contains(NERDY)) {
            myViewHolder.nerdyTag.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {

        // If no items are present, there's no need for loader
        if (data == null || data.size() == 0) {
            return 0;
        }

        // +1 for loader
        return data.size() + 1;
    }

    @Override
    public long getItemId(int position) {

        // loader can't be at position 0
        // loader can only be at the last position
        if (position != 0 && position == getItemCount() - 1) {

            // id of loader is considered as -1 here
            return -1;
        }
        return position;
    }

    public void setItems(List<Joke> items) {
        data = items;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.jokeText)
        TextView jokeText;

        @BindView(R.id.jokeNumber)
        TextView jokeNumber;

        @BindView((R.id.nerdy))
        TextView nerdyTag;

        @BindView((R.id.explicit))
        TextView explicitTag;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
