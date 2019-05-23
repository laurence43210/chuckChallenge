package chuck.com.challenge.ui.infiniteList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import chuck.com.challenge.R;
import chuck.com.challenge.contracts.infiniteList.InfiniteListFragmentContract;
import chuck.com.challenge.data.models.Joke;
import chuck.com.challenge.presenters.BatchJokePresenter;
import chuck.com.challenge.ui.base.BaseFragment;
import chuck.com.challenge.adapters.JokeListAdapter;
import chuck.com.challenge.appEnums.ContentValuesEnum;
import chuck.com.challenge.appListeners.InfiniteListListener;
import chuck.com.challenge.helpers.DialogHelper;
import chuck.com.challenge.helpers.UIHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class InfiniteListFragment extends BaseFragment implements InfiniteListFragmentContract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    DialogHelper dialogHelper;

    @Inject
    UIHelper uIHelper;

    @Inject
    JokeListAdapter jokeListAdapter;

    @Inject
    BatchJokePresenter presenter;

    private List<Joke> jokes = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_infinite_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.attachView(this);
        presenter.fetchBatchOfRandomJokes();
    }

    protected void initUI() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(jokeListAdapter);


        recyclerView.addOnScrollListener(new InfiniteListListener() {
            @Override
            public void onLoadMore() {
                presenter.fetchBatchOfRandomJokes();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.destroyAllDisposables();
        presenter.detachView();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if (savedInstanceState != null
                && savedInstanceState
                        .containsKey(ContentValuesEnum.RECYCLER_VIEW.getKey())
                && savedInstanceState
                        .containsKey(ContentValuesEnum.RECYCLER_VIEW_DATA
                                .getKey())) {

            jokes = (List<Joke>) savedInstanceState
                    .getSerializable(ContentValuesEnum.RECYCLER_VIEW_DATA
                            .getKey());

            ((JokeListAdapter) recyclerView.getAdapter()).setItems(jokes);

            recyclerView.getLayoutManager().onRestoreInstanceState(
                    savedInstanceState
                            .getParcelable(ContentValuesEnum.RECYCLER_VIEW
                                    .getKey()));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(ContentValuesEnum.RECYCLER_VIEW.getKey(),
                recyclerView.getLayoutManager().onSaveInstanceState());
        outState.putSerializable(ContentValuesEnum.RECYCLER_VIEW_DATA.getKey(),
                (Serializable) jokes);
    }

    @Override
    public void onJokesLoaded(List<Joke> jokeEntries) {
        jokes.addAll(jokeEntries);
        jokeListAdapter.setItems(jokes);
        jokeListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String message) {
        dialogHelper.getErrorDialog(getActivity(), message).show();
    }
}
