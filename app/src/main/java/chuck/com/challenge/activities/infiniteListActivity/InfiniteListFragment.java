package chuck.com.challenge.activities.infiniteListActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import android.content.ContentValues;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import chuck.com.challenge.Constants;
import chuck.com.challenge.R;
import chuck.com.challenge.responsePojo.JokeEntry;
import chuck.com.challenge.responsePojo.ResponseParent;
import chuck.com.challenge.appEnums.ContentValuesEnum;
import chuck.com.challenge.appEnums.ServerCallEnum;
import chuck.com.challenge.activities.baseActivity.BaseFragment;
import chuck.com.challenge.adapters.JokeListAdapter;
import chuck.com.challenge.helpers.DialogHelper;
import chuck.com.challenge.helpers.SharedPreferencesHelper;
import chuck.com.challenge.helpers.VolleyHelper;
import chuck.com.challenge.listeners.InfiniteListListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class InfiniteListFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    JokeListAdapter jokeListAdapter;

    private List<JokeEntry> jokeEntries = new ArrayList<>();

    public InfiniteListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infinite_list,
                container, false);
        ButterKnife.bind(this, view);
        initUI();
        return view;
    }

    private void initUI() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        jokeListAdapter = new JokeListAdapter(getActivity());
        recyclerView.setAdapter(jokeListAdapter);

        requestNewBatch(true);

        recyclerView.addOnScrollListener(new InfiniteListListener() {
            @Override
            public void onLoadMore() {
                jokeListAdapter.showLoading(true);
                jokeListAdapter.notifyDataSetChanged();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        requestNewBatch(false);
                    }
                }, 1000);

            }
        });
    }

    private void requestNewBatch(final boolean firstLoad) {
        if (firstLoad) {
            mListener.showProgressSpinner();
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContentValuesEnum.JOKES_TO_RETRIEVE.getKey(),
                Constants.BATCH_JOKE_QUANTITY);
        contentValues.put(ContentValuesEnum.RESTRICT_EXPLICIT.getKey(),
                SharedPreferencesHelper.isNonExplicitsEnabled());
        VolleyHelper.makeVolleyCall(ServerCallEnum.RANDOM, contentValues,
                new Response.Listener<ResponseParent>() {
                    @Override
                    public void onResponse(ResponseParent response) {

                        if (response.getValues() != null
                                && !response.getValues().isEmpty()) {

                            jokeEntries.addAll(response.getValues());
                            jokeListAdapter.setItems(jokeEntries);
                            jokeListAdapter.showLoading(false);
                            jokeListAdapter.notifyDataSetChanged();

                            if (firstLoad) {
                                mListener.hideProgressSpinner();
                            }
                        } else {
                            DialogHelper.getErrorDialog(getActivity());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        DialogHelper.getErrorDialog(getActivity());
                    }
                });
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

            jokeEntries = (List<JokeEntry>) savedInstanceState
                    .getSerializable(ContentValuesEnum.RECYCLER_VIEW_DATA
                            .getKey());

            ((JokeListAdapter) recyclerView.getAdapter()).setItems(jokeEntries);

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
                (Serializable) jokeEntries);
    }
}
