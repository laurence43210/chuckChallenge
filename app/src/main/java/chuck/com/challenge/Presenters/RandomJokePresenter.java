package chuck.com.challenge.Presenters;

import java.util.List;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;

import chuck.com.challenge.R;
import chuck.com.challenge.Models.AsyncJokeRetriever;
import chuck.com.challenge.appListeners.ILaunchView;
import chuck.com.challenge.appListeners.IRandomJokePresenter;
import chuck.com.challenge.appListeners.OnJokeRetrievedListener;
import chuck.com.challenge.helpers.ResourceHelper;
import chuck.com.challenge.helpers.UIHelper;
import chuck.com.challenge.responsePojo.JokeEntry;

/**
 * Created by Laurence on 12/10/2016.
 */
public class RandomJokePresenter implements IRandomJokePresenter,
        OnJokeRetrievedListener {

    private ILaunchView view;

    private AsyncJokeRetriever model;

    public RandomJokePresenter(ILaunchView view) {
        this.view = view;
        this.model = new AsyncJokeRetriever();
    }

    @Override
    public void fetchJoke() {
        model.RetrieveRandomJoke(this);
    }

    @Override
    public void onSuccessBatch(List<JokeEntry> batch) {
    }

    @Override
    public void onSuccessSingle(JokeEntry joke) {
        String jokeTitle = String.format(
                ResourceHelper.getString(R.string.generic_dialog_title),
                String.valueOf(joke.getId()));

        SpannableString titleSpan = new SpannableString(jokeTitle);
        titleSpan.setSpan(new StyleSpan(Typeface.BOLD), jokeTitle.length()
                - String.valueOf(joke.getId()).length(), jokeTitle.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        view.onRandomJokeLoaded(titleSpan,
                UIHelper.convertStringFromHtml(joke.getJoke()));
    }

    @Override
    public void onFail() {
        view.onProblem("there was a problem");
    }
}
