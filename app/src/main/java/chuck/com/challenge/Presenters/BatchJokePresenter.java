package chuck.com.challenge.Presenters;

import java.util.List;

import chuck.com.challenge.Models.AsyncJokeRetriever;
import chuck.com.challenge.appListeners.IBatchJokePresenter;
import chuck.com.challenge.appListeners.IBatchJokeView;
import chuck.com.challenge.appListeners.OnJokeRetrievedListener;
import chuck.com.challenge.responsePojo.JokeEntry;

/**
 * Created by Laurence on 12/10/2016.
 */
public class BatchJokePresenter implements IBatchJokePresenter,
        OnJokeRetrievedListener {

    private IBatchJokeView view;

    private AsyncJokeRetriever model;

    public BatchJokePresenter(IBatchJokeView view) {
        this.view = view;
        this.model = new AsyncJokeRetriever();
    }

    @Override
    public void onSuccessBatch(List<JokeEntry> batch) {
        view.onJokesLoaded(batch);
    }

    @Override
    public void onSuccessSingle(JokeEntry joke) {
    }

    @Override
    public void onFail(String error) {
        view.onError(error);
    }

    @Override
    public void fetchBatchOfRandomJokes() {
        model.RetrieveBatchJokes(this);
    }
}
