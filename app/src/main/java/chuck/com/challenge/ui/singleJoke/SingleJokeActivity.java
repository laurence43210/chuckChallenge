package chuck.com.challenge.ui.singleJoke;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import chuck.com.challenge.R;
import chuck.com.challenge.ui.base.BaseActivity;


public class SingleJokeActivity extends BaseActivity {

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
        for (Fragment fragment : getSupportFragmentManager()
                .getFragments()) {
            if (fragment instanceof SingleJokeFragment) {
                ((SingleJokeFragment) fragment).loadJoke();
                break;
            }
        }
    }
}
