package chuck.com.challenge.activities.launchActivity;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import chuck.com.challenge.R;
import chuck.com.challenge.activities.baseActivity.BaseActivity;


public class LaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ButterKnife.bind(this);
        disableScrollOnToolbar();
        hideCollapsingTitle();
        hideDefaultTitle();
    }


    @OnClick(R.id.showRandomJoke)
    @Optional
    void onShowNextRandomJoke() {
        for (android.support.v4.app.Fragment fragment : getSupportFragmentManager()
                .getFragments()) {
            if (fragment instanceof SingleJokeFragment) {
                ((SingleJokeFragment) fragment).loadJoke();
                break;
            }
        }
    }
}
