package chuck.com.challenge.adapters;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import chuck.com.challenge.R;
import chuck.com.challenge.Classes.JokeEntry;
import chuck.com.challenge.helpers.UIHelper;

/**
 * Created by Laurence on 17/09/2016.
 */
public class JokeListAdapter extends
        RecyclerView.Adapter<JokeListAdapter.myViewHolder> {

    List<JokeEntry> data;


    public JokeListAdapter(List<JokeEntry> data) {
        this.data = data;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_infinite_list, parent, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        JokeEntry jokeEntry = data.get(position);
        holder.jokeText.setText(UIHelper.convertStringFromHtml(jokeEntry
                .getJoke()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class myViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        @BindView(R.id.jokeText)
        TextView jokeText;

        public myViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
