package chuck.com.challenge.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import chuck.com.challenge.Constants;
import chuck.com.challenge.R;
import chuck.com.challenge.responsePojo.JokeEntry;
import chuck.com.challenge.helpers.ResourceHelper;
import chuck.com.challenge.helpers.UIHelper;

/**
 * Created by Laurence on 17/09/2016.
 */
public class JokeListAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected boolean showLoader;

    private static final int VIEWTYPE_ITEM = 1;

    private static final int VIEWTYPE_LOADER = 2;

    List<JokeEntry> data = new ArrayList<>();

    protected LayoutInflater mInflater;

    public JokeListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup,
            int viewType) {
        if (viewType == VIEWTYPE_LOADER) {

            // Your LoaderViewHolder class
            return new ProgressViewHolder(mInflater.inflate(
                    R.layout.adapter_progress_bar, viewGroup, false));

        } else if (viewType == VIEWTYPE_ITEM) {
            return new MyViewHolder(mInflater.inflate(
                    R.layout.adapter_infinite_list, viewGroup, false));
        }

        throw new IllegalArgumentException("Invalid ViewType: " + viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,
            int position) {

        // Loader ViewHolder
        if (viewHolder instanceof ProgressViewHolder) {
            ProgressViewHolder loaderViewHolder = (ProgressViewHolder) viewHolder;
            if (showLoader) {
                loaderViewHolder.progressBar.setVisibility(View.VISIBLE);
            } else {
                loaderViewHolder.progressBar.setVisibility(View.GONE);
            }
            return;
        }
        // joke ViewHolder
        if (viewHolder instanceof MyViewHolder) {

            MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
            JokeEntry jokeEntry = data.get(position);
            myViewHolder.jokeText.setText(UIHelper
                    .convertStringFromHtml(jokeEntry.getJoke()));

            myViewHolder.lessonNumber.setText(String.format(
                    ResourceHelper.getString(R.string.generic_dialog_title),
                    String.valueOf(jokeEntry.getId())));

            if (jokeEntry.getCategories().contains(Constants.EXPLICIT)) {
                myViewHolder.explicitTag.setVisibility(View.VISIBLE);
            }
            if (jokeEntry.getCategories().contains(Constants.NERDY)) {
                myViewHolder.nerdyTag.setVisibility(View.VISIBLE);
            }
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

    @Override
    public int getItemViewType(int position) {

        // loader can't be at position 0
        // loader can only be at the last position
        if (position != 0 && position == getItemCount() - 1) {
            return VIEWTYPE_LOADER;
        }

        return VIEWTYPE_ITEM;
    }

    public void showLoading(boolean status) {
        showLoader = status;
    }

    public void setItems(List<JokeEntry> items) {
        data = items;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.jokeText)
        TextView jokeText;

        @BindView(R.id.lessonText)
        TextView lessonNumber;

        @BindView((R.id.nerdy))
        TextView nerdyTag;

        @BindView((R.id.explicit))
        TextView explicitTag;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.progressBar)
        ProgressBar progressBar;

        public ProgressViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
