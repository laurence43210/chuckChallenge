package chuck.com.challenge.activities.baseActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import chuck.com.challenge.R;
import chuck.com.challenge.activities.infiniteListActivity.InfiniteListActivity;
import chuck.com.challenge.activities.launchActivity.LaunchActivity;
import chuck.com.challenge.activities.nameReplaceActivity.NameReplaceActivity;
import chuck.com.challenge.listeners.GlobalListener;

public class BaseActivity extends AppCompatActivity implements GlobalListener {

    @BindView(R.id.activityContent)
    FrameLayout frameLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.navigationView)
    NavigationView navigationView;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    private ProgressBar progressBar;

    private ActionBarDrawerToggle actionBarDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setUpNavigationView();
    }

    private void setUpNavigationView() {

        navigationView
                .setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {

                        if (item.isChecked())
                            item.setChecked(false);
                        else
                            item.setChecked(true);

                        //Closing drawer on item click
                        drawerLayout.closeDrawers();

                        //Check to see which item was being clicked and perform appropriate action
                        switch (item.getItemId()) {

                            case R.id.home:
                                if (!(BaseActivity.this instanceof LaunchActivity))
                                    goToActivity(LaunchActivity.class);
                                break;

                            case R.id.nameReplace:
                                if (!(BaseActivity.this instanceof NameReplaceActivity))
                                    goToActivity(NameReplaceActivity.class);
                                break;

                            case R.id.infiniteList:
                                if (!(BaseActivity.this instanceof InfiniteListActivity))
                                    goToActivity(InfiniteListActivity.class);
                                break;

                        }

                        return false;
                    }
                });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.drawer_open, R.string.drawer_closed) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
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

    @Override
    public void showProgressSpinner() {
       showProgressBar();
    }

    @Override
    public void hideProgressSpinner() {
        hideProgressBar();
    }

    private void goToNewActivity(Class clazz, int flags) {
        Intent intent = new Intent(this, clazz);
        if (flags > 0) {
            intent.addFlags(flags);
        }
        startActivity(intent);
    }

    private ProgressBar showProgressBar() {

        progressBar = new ProgressBar(new ContextThemeWrapper(this,
                R.style.loadingProgressSpinner));
        progressBar.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);

        if (root != null) {
            root.addView(progressBar);
        }
        return progressBar;
    }

    private void hideProgressBar() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
