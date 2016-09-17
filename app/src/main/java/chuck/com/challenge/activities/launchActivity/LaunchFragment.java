package chuck.com.challenge.activities.launchActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.OnClick;
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
        View view = inflater
                .inflate(R.layout.fragment_launch, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.randomJokeButton)
    void showRandomJoke() {
    }

    @OnClick(R.id.nameReplaceButton)
    void goToNameReplace() {
    }

    @OnClick(R.id.infiniteListButton)
    void goToInfiniteList() {
    }

}
