package chuck.com.challenge.appListeners;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Laurence on 17/09/2016.
 */
public abstract class InfiniteListListener extends
        RecyclerView.OnScrollListener {

    private static final String TAG = "RecylcerScrollListener";

    private int previousTotal = 0; // The total number of items in the dataset after the last load

    private boolean loading = true; // True if we are still waiting for the last set of data to load.

    private static final int visibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.

    private int firstVisibleItem, visibleItemCount, totalItemCount;

    public InfiniteListListener() {
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();

        visibleItemCount = recyclerView.getChildCount();
        if (manager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;
            firstVisibleItem = linearLayoutManager
                    .findFirstVisibleItemPosition();
            totalItemCount = linearLayoutManager.getItemCount();
        }

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }

        if (!loading
                && (totalItemCount - visibleItemCount <= firstVisibleItem
                        + visibleThreshold)) {
            // End has been reached
            // do something
            onLoadMore();
            loading = true;
        }

    }

    public abstract void onLoadMore();

}
