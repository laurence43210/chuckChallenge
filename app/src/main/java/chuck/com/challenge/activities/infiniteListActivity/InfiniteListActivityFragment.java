package chuck.com.challenge.activities.infiniteListActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chuck.com.challenge.R;
import chuck.com.challenge.activities.baseActivity.BaseFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class InfiniteListActivityFragment extends BaseFragment {

    public InfiniteListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_infinite_list, container,
                false);
    }
}
