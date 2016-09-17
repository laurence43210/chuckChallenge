package chuck.com.challenge.activities.launchActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import chuck.com.challenge.Constants;
import chuck.com.challenge.R;
import chuck.com.challenge.Classes.JokeEntry;
import chuck.com.challenge.Classes.ResponseParent;
import chuck.com.challenge.Enums.ContentValuesEnum;
import chuck.com.challenge.Enums.ServerCallEnum;
import chuck.com.challenge.activities.baseActivity.BaseFragment;
import chuck.com.challenge.helpers.VolleyHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class LaunchFragment extends BaseFragment {

    public LaunchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_launch, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.randomJokeButton)
    void showRandomJoke() {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ContentValuesEnum.JOKES_TO_RETRIEVE.getKey(),
                Constants.SINGLE_JOKE_QUANTITY);

        VolleyHelper.makeVolleyCall(ServerCallEnum.RANDOM, contentValues,
                new Response.Listener<ResponseParent>() {
                    @Override
                    public void onResponse(ResponseParent response) {
                        if (response.getValues() != null
                                && !response.getValues().isEmpty()) {
                            JokeEntry joke = response.getValues().get(0);
                            Toast.makeText(getActivity(), joke.getJoke(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

    }

    @OnClick(R.id.nameReplaceButton)
    void goToNameReplace() {
    }

    @OnClick(R.id.infiniteListButton)
    void goToInfiniteList() {
    }

}
