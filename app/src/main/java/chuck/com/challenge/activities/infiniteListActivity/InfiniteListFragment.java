package chuck.com.challenge.activities.infiniteListActivity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import chuck.com.challenge.R;
import chuck.com.challenge.activities.baseActivity.BaseFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class InfiniteListFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public InfiniteListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infinite_list,
                container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {
        
    }
}
