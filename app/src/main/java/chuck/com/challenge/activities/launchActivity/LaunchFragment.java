package chuck.com.challenge.activities.launchActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import chuck.com.challenge.Constants;
import chuck.com.challenge.R;
import chuck.com.challenge.Classes.JokeEntry;
import chuck.com.challenge.Classes.ResponseParent;
import chuck.com.challenge.Enums.ContentValuesEnum;
import chuck.com.challenge.Enums.ServerCallEnum;
import chuck.com.challenge.activities.baseActivity.BaseFragment;
import chuck.com.challenge.activities.infiniteListActivity.InfiniteListActivity;
import chuck.com.challenge.activities.nameReplaceActivity.NameReplaceActivity;
import chuck.com.challenge.helpers.DialogHelper;
import chuck.com.challenge.helpers.SharedPreferencesHelper;
import chuck.com.challenge.helpers.VolleyHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class LaunchFragment extends BaseFragment {

    @BindView(R.id.explicitsCheckbox)
    CheckBox explicitsCheckbox;

    public LaunchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_launch, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;

    }

    private void init() {
        explicitsCheckbox.setChecked(SharedPreferencesHelper.isNonExplicitsEnabled());

        explicitsCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferencesHelper.setNonExplicits(isChecked);
            }
        });
    }

    @OnClick(R.id.randomJokeButton)
    void showRandomJoke() {

        ContentValues contentValues = new ContentValues();
        contentValues.put(ContentValuesEnum.JOKES_TO_RETRIEVE.getKey(),
                Constants.SINGLE_JOKE_QUANTITY);
        contentValues.put(ContentValuesEnum.RESTRICT_EXPLICIT.getKey(),
                SharedPreferencesHelper.isNonExplicitsEnabled());

        VolleyHelper.makeVolleyCall(ServerCallEnum.RANDOM, contentValues,
                new Response.Listener<ResponseParent>() {
                    @Override
                    public void onResponse(ResponseParent response) {
                        if (response.getValues() != null
                                && !response.getValues().isEmpty()) {
                            JokeEntry joke = response.getValues().get(0);
                            DialogHelper.getSuccessDialog(getActivity(), joke)
                                    .show();

                        } else {
                            DialogHelper.getErrorDialog(getActivity()).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        DialogHelper.getErrorDialog(getActivity()).show();
                    }
                });

    }

    @OnClick(R.id.nameReplaceButton)
    void goToNameReplace() {
        mListener.goToActivity(NameReplaceActivity.class);
    }

    @OnClick(R.id.infiniteListButton)
    void goToInfiniteList() {
        mListener.goToActivity(InfiniteListActivity.class);
    }

}
