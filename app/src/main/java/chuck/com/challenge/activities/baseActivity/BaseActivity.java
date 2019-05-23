package chuck.com.challenge.activities.baseActivity;

import javax.inject.Inject;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import chuck.com.challenge.ChuckChallengeApplication;
import chuck.com.challenge.R;
import chuck.com.challenge.activities.infiniteListActivity.InfiniteListActivity;
import chuck.com.challenge.activities.nameReplaceActivity.ReplaceNameActivity;
import chuck.com.challenge.activities.singleJokeActivity.SingleJokeActivity;
import chuck.com.challenge.appListeners.GlobalListener;
import chuck.com.challenge.helpers.ResourceHelper;
import chuck.com.challenge.helpers.SharedPreferencesHelper;
import dagger.android.support.DaggerAppCompatActivity;

public class BaseActivity extends DaggerAppCompatActivity implements GlobalListener {

    @BindView(R.id.activityContent)
    FrameLayout frameLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.navigationView)
    NavigationView navigationView;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolbarImage)
    ImageView toolbarImage;

    @Inject
    ResourceHelper resourceHelper;

    @Inject
    SharedPreferencesHelper sharedPreferencesHelper;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setUpNavigationView();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            hideToolbarImage();
            hideCollapsingTitle();
        }
    }

    private void setUpNavigationView() {

        navigationView
                .setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {

                        //Closing drawer on item click
                        drawerLayout.closeDrawers();

                        //Check to see which item was being clicked and perform appropriate action
                        switch (item.getItemId()) {

                        case R.id.home:
                            if (!(BaseActivity.this instanceof SingleJokeActivity))
                                goToActivity(SingleJokeActivity.class);
                            break;

                        case R.id.nameReplace:
                            if (!(BaseActivity.this instanceof ReplaceNameActivity))
                                goToActivity(ReplaceNameActivity.class);
                            break;

                        case R.id.infiniteList:
                            if (!(BaseActivity.this instanceof InfiniteListActivity))
                                goToActivity(InfiniteListActivity.class);
                            break;

                        }

                        return false;
                    }
                });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.drawer_open,
                R.string.drawer_closed) {

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
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    @Override
    public void showProgressSpinner() {
        showProgressBar();
    }

    @Override
    public void hideProgressSpinner() {
        hideProgressBar();
    }

    /**
     *Sets a Drawable as the image to use in the toolbar.
     *
     * @param id the image R address
     */
    public void setToolbarImage(int id) {
        toolbarImage.setImageDrawable(resourceHelper.getDrawable(id));
    }

    /**
     * Manipulate the collapsible toolbar to not display an image.
     */

    public void hideToolbarImage() {
        toolbarImage.setVisibility(View.GONE);
    }

    /**
     * Manipulate the collapsible toolbar layout to not have an animated collapsible
     * title, and instead default the text in the toolbar.
     */
    public void hideCollapsingTitle() {
        collapsingToolbarLayout.setTitleEnabled(false);
    }

    /**
     * Manipulate the toolbar to show no title text at all.
     * May need to be called with hideCollapsingTitle() for best effect.
     */

    public void hideDefaultTitle() {
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    /**
     * Manipulate the collapsible toolbar to not follow the scroll flags defined in the base activity xml.
     * This will stop the scrolling functionality for working and freeze the toolbar's position
     */

    public void disableScrollOnToolbar() {
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) collapsingToolbarLayout
                .getLayoutParams();
        params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP);
        collapsingToolbarLayout.setLayoutParams(params);
    }

    /**
     *Constructs a progress spinner that will be displayed
     *  in the centre of the system window on any activity
     *
     * @return   Activity independent progress spinner
     */
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

    /**
     *Hides the visible progress spinner.
     */

    private void hideProgressBar() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_base, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem checkable = menu.findItem(R.id.noExplicits);
        checkable.setChecked(sharedPreferencesHelper.getExplicitState());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.noExplicits:
            boolean isChecked = !item.isChecked();
            item.setChecked(isChecked);
            sharedPreferencesHelper.setExplicits(isChecked);
            return true;
        default:
            return false;
        }
    }

}
