package chuck.com.challenge.activities.launchActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chuck.com.challenge.R;
import chuck.com.challenge.activities.baseActivity.BaseFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class LaunchFragment extends BaseFragment {

    public LaunchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_launch, container, false);
    }
}
