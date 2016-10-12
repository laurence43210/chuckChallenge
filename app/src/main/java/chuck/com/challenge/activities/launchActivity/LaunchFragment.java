package chuck.com.challenge.activities.launchActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import chuck.com.challenge.R;
import chuck.com.challenge.activities.baseActivity.BaseFragment;

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
        init();
        return view;

    }

    private void init() {

        //                mListener.showProgressSpinner();
        //                ContentValues contentValues = new ContentValues();
        //                contentValues.put(ContentValuesEnum.JOKES_TO_RETRIEVE.getKey(),
        //                        Constants.SINGLE_JOKE_QUANTITY);
        //                contentValues.put(ContentValuesEnum.RESTRICT_EXPLICIT.getKey(),
        //                        SharedPreferencesHelper.isNonExplicitsEnabled());
        //
        //                VolleyHelper.makeVolleyCall(ServerCallEnum.RANDOM,
        //                        contentValues, new Response.Listener<ResponseParent>() {
        //                            @Override
        //                            public void onResponse(ResponseParent response) {
        //                                mListener.hideProgressSpinner();
        //                                if (response.getValues() != null
        //                                        && !response.getValues().isEmpty()) {
        //                                    JokeEntry joke = response.getValues()
        //                                            .get(0);
        //                                    DialogHelper.getSuccessDialog(
        //                                            getActivity(), joke).show();
        //
        //                                } else {
        //                                    DialogHelper.getErrorDialog(getActivity())
        //                                            .show();
        //                                }
        //                            }
        //                        }, new Response.ErrorListener() {
        //                            @Override
        //                            public void onErrorResponse(VolleyError error) {
        //                                mListener.hideProgressSpinner();
        //                                DialogHelper.getErrorDialog(getActivity())
        //                                        .show();
        //                            }
        //                        });
        //            }
        //        });
    }

}
