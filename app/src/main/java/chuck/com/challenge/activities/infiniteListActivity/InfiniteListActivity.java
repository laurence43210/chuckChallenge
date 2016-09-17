package chuck.com.challenge.activities.infiniteListActivity;

import android.os.Bundle;

import chuck.com.challenge.R;
import chuck.com.challenge.Enums.ServerCallEnum;
import chuck.com.challenge.activities.baseActivity.BaseActivity;
import chuck.com.challenge.helpers.VolleyHelper;

public class InfiniteListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinite_list);
    }

    @Override
    protected void onStop() {
        super.onStop();
        VolleyHelper.cancelAllRequests(ServerCallEnum.NAME_REPLACE.toString());
    }

}
