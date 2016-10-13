package chuck.com.challenge.activities.infiniteListActivity;

import android.os.Bundle;

import chuck.com.challenge.R;
import chuck.com.challenge.activities.baseActivity.BaseActivity;

public class InfiniteListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinite_list);
        setToolbarImage(R.drawable.chuck_desert);
    }
}
