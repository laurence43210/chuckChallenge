package chuck.com.challenge.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import chuck.com.challenge.R;

/**
 * Created by Laurence on 17/09/2016.
 */
public class JokeListAdapter extends RecyclerView.Adapter<JokeListAdapter.myViewHolder> {


    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class myViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case

        @BindView(R.id.jokeText) TextView jokeText;

        public myViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
