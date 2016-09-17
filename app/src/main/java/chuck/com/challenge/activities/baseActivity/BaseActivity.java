package chuck.com.challenge.activities.baseActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import chuck.com.challenge.R;
import chuck.com.challenge.listeners.GlobalListener;

public class BaseActivity extends AppCompatActivity implements GlobalListener {

    @BindView(R.id.activityContent)
    FrameLayout frameLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(layoutResID, frameLayout);
    }

    @Override
    public void goToActivity(Class clazz) {
        goToNewActivity(clazz, 0);
    }

    @Override
    public void goToActivity(Class clazz, int flags) {
        goToNewActivity(clazz, flags);
    }

    private void goToNewActivity(Class clazz, int flags) {
        Intent intent = new Intent(this, clazz);
        if (flags > 0) {
            intent.addFlags(flags);
        }
        startActivity(intent);
    }

}
