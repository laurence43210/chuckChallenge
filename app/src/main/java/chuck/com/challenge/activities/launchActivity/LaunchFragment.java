package chuck.com.challenge.activities.launchActivity;


import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import chuck.com.challenge.Presenters.RandomJokePresenter;
import chuck.com.challenge.R;
import chuck.com.challenge.activities.baseActivity.BaseFragment;
import chuck.com.challenge.appListeners.ILaunchView;
import chuck.com.challenge.helpers.DialogHelper;


public class LaunchFragment extends BaseFragment implements ILaunchView {

    @BindView(R.id.titleSwitcher)
    TextSwitcher titleSwitcher;

    @BindView(R.id.jokeSwitcher)
    TextSwitcher jokeSwitcher;

    RandomJokePresenter randomJokePresenter;

    private ViewSwitcher.ViewFactory titleFactory = new ViewSwitcher.ViewFactory() {

        @Override
        public View makeView() {
            TextView t = new TextView(getActivity());
            t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                t.setTextAppearance(R.style.Content_Large);
            } else {
                t.setTextAppearance(getActivity(), R.style.Content);
            }

            return t;
        }
    };

    private ViewSwitcher.ViewFactory jokeFactory = new ViewSwitcher.ViewFactory() {

        @Override
        public View makeView() {
            TextView t = new TextView(getActivity());
            t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                t.setTextAppearance(R.style.Content_Large_Dark);
            } else {
                t.setTextAppearance(getActivity(), R.style.Content_Dark);
            }
            return t;
        }
    };

    public LaunchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_launch, container, false);
        ButterKnife.bind(this, view);
        randomJokePresenter = new RandomJokePresenter(this);
        init();
        return view;
    }

    // public void loadNewJoke() {
    //        mListener.showProgressSpinner();
    //        ContentValues contentValues = new ContentValues();
    //        contentValues.put(ContentValuesEnum.JOKES_TO_RETRIEVE.getKey(),
    //                Constants.SINGLE_JOKE_QUANTITY);
    //        contentValues.put(ContentValuesEnum.RESTRICT_EXPLICIT.getKey(),
    //                SharedPreferencesHelper.isNonExplicitsEnabled());
    //
    //        VolleyHelper.makeVolleyCall(ServerCallEnum.RANDOM, contentValues,
    //                new Response.Listener<ResponseParent>() {
    //                    @Override
    //                    public void onResponse(ResponseParent response) {
    //                        mListener.hideProgressSpinner();
    //                        if (response.getValues() != null
    //                                && !response.getValues().isEmpty()) {
    //                            JokeEntry joke = response.getValues().get(0);
    //
    //                            String jokeTitle = String.format(ResourceHelper
    //                                    .getString(R.string.generic_dialog_title),
    //                                    String.valueOf(joke.getId()));
    //
    //                            SpannableString spannableString = new SpannableString(
    //                                    jokeTitle);
    //                            spannableString.setSpan(
    //                                    new StyleSpan(Typeface.BOLD),
    //                                    jokeTitle.length()
    //                                            - String.valueOf(joke.getId())
    //                                                    .length(),
    //                                    jokeTitle.length(),
    //                                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    //
    //                            titleSwitcher.setText(spannableString);
    //                            jokeSwitcher.setText(UIHelper
    //                                    .convertStringFromHtml(joke.getJoke()));
    //
    //                        } else {
    //                            DialogHelper.getErrorDialog(getActivity()).show();
    //                        }
    //                    }
    //                }, new Response.ErrorListener() {
    //                    @Override
    //                    public void onErrorResponse(VolleyError error) {
    //                        mListener.hideProgressSpinner();
    //                        DialogHelper.getErrorDialog(getActivity()).show();
    //                    }
    //                });
    //}

    private void init() {
        titleSwitcher.setFactory(titleFactory);
        titleSwitcher.setAnimateFirstView(false);
        titleSwitcher.setOutAnimation(getContext(), R.anim.slide_right_to_left);
        titleSwitcher.setInAnimation(getContext(), R.anim.slide_left_to_right);

        jokeSwitcher.setFactory(jokeFactory);
        jokeSwitcher.setAnimateFirstView(false);
        jokeSwitcher.setOutAnimation(getContext(), R.anim.slide_right_to_left);
        jokeSwitcher.setInAnimation(getContext(), R.anim.slide_left_to_right);

        randomJokePresenter.fetchJoke();

    }


    @Override
    public void onRandomJokeLoaded(SpannableString title, String joke) {
        titleSwitcher.setText(title);
        jokeSwitcher.setText(joke);
    }

    @Override
    public void onProblem(String string) {
        DialogHelper.getErrorDialog(getActivity()).show();
    }

}
