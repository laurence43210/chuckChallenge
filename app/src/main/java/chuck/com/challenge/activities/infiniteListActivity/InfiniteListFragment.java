package chuck.com.challenge.activities.infiniteListActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import chuck.com.challenge.Constants;
import chuck.com.challenge.R;
import chuck.com.challenge.Classes.ResponseParent;
import chuck.com.challenge.Enums.ContentValuesEnum;
import chuck.com.challenge.Enums.ServerCallEnum;
import chuck.com.challenge.activities.baseActivity.BaseFragment;
import chuck.com.challenge.adapters.JokeListAdapter;
import chuck.com.challenge.helpers.DialogHelper;
import chuck.com.challenge.helpers.VolleyHelper;
import chuck.com.challenge.listeners.InfiniteListListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class InfiniteListFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    JokeListAdapter jokeListAdapter;

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
        linearLayoutManager
                .setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(new InfiniteListListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                requestNewBatch();
            }
        });

        jokeListAdapter = new JokeListAdapter();
        recyclerView.setAdapter(jokeListAdapter);
        requestNewBatch();

    }

    private void requestNewBatch() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContentValuesEnum.JOKES_TO_RETRIEVE.getKey(),
                Constants.BATCH_JOKE_QUANTITY);

        VolleyHelper.makeVolleyCall(ServerCallEnum.RANDOM, contentValues,
                new Response.Listener<ResponseParent>() {
                    @Override
                    public void onResponse(ResponseParent response) {

                        if (response.getValues() != null
                                && !response.getValues().isEmpty()) {

                            jokeListAdapter.addNewData(response.getValues());
                            recyclerView.getAdapter().notifyDataSetChanged();

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

}
