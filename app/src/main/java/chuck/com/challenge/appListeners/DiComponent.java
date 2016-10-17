package chuck.com.challenge.appListeners;


import javax.inject.Singleton;

import chuck.com.challenge.DependencyModules.AppModule;
import chuck.com.challenge.DependencyModules.HelperModule;
import chuck.com.challenge.DependencyModules.NetModule;
import chuck.com.challenge.Models.AsyncJokeRetriever;
import chuck.com.challenge.Presenters.ReplaceNamePresenter;
import chuck.com.challenge.Presenters.SingleJokePresenter;
import chuck.com.challenge.activities.baseActivity.BaseActivity;
import chuck.com.challenge.activities.infiniteListActivity.InfiniteListFragment;
import chuck.com.challenge.activities.nameReplaceActivity.ReplaceNameFragment;
import chuck.com.challenge.activities.singleJokeActivity.SingleJokeFragment;
import chuck.com.challenge.adapters.JokeListAdapter;
import dagger.Component;

/**
 * Created by Laurence on 14/10/2016.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class, HelperModule.class})

public interface DiComponent {

    void inject (BaseActivity baseActivity);

    void inject (AsyncJokeRetriever asyncJokeRetriever);

    void inject(JokeListAdapter jokeListAdapter);

    void inject(SingleJokePresenter singleJokePresenter);

    void inject(ReplaceNamePresenter replaceNamePresenter);

    void inject(InfiniteListFragment infiniteListFragment);

    void inject(SingleJokeFragment singleJokeFragment);

    void inject(ReplaceNameFragment replaceNameFragment);
}
