package chuck.com.challenge.activities.launchActivity;

import android.os.Bundle;

import chuck.com.challenge.R;
import chuck.com.challenge.enums.ServerCallEnum;
import chuck.com.challenge.activities.baseActivity.BaseActivity;
import chuck.com.challenge.helpers.VolleyHelper;

public class LaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        disableScrollOnToolbar();
        hideCollapsingTitle();
        hideDefaultTitle();
    }

    @Override
    protected void onStop() {
        super.onStop();
        VolleyHelper.cancelAllRequests(ServerCallEnum.RANDOM.toString());
    }
}
