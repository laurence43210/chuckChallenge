package chuck.com.challenge.ui.nameReplace;

import android.os.Bundle;

import chuck.com.challenge.R;
import chuck.com.challenge.ui.base.BaseActivity;


public class ReplaceNameActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_replace);
        hideToolbarImage();
        hideCollapsingTitle();
    }
}
