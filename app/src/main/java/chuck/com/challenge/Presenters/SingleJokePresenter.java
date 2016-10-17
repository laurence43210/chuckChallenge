package chuck.com.challenge.Presenters;

import java.util.List;

import javax.inject.Inject;

import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;

import chuck.com.challenge.ChuckChallengeApplication;
import chuck.com.challenge.R;
import chuck.com.challenge.Models.AsyncJokeRetriever;
import chuck.com.challenge.appListeners.ISingleJokePresenter;
import chuck.com.challenge.appListeners.ISingleJokeView;
import chuck.com.challenge.appListeners.OnJokeRetrievedListener;
import chuck.com.challenge.exceptions.NonSplittableNameException;
import chuck.com.challenge.helpers.ResourceHelper;
import chuck.com.challenge.helpers.UIHelper;
import chuck.com.challenge.responsePojo.JokeEntry;

/**
 * Created by Laurence on 12/10/2016.
 */
public class SingleJokePresenter implements ISingleJokePresenter,
        OnJokeRetrievedListener {

    @Inject
    ResourceHelper resourceHelper;

    @Inject UIHelper uiHelper;

    private ISingleJokeView view;

    private AsyncJokeRetriever model;

    public SingleJokePresenter(ISingleJokeView view) {
        ChuckChallengeApplication.getDiComponent().inject(this);
        this.view = view;
        this.model = new AsyncJokeRetriever();
    }

    @Override
    public void fetchSingleRandomJoke() {
        model.RetrieveRandomJoke(this);
    }

    @Override
    public void fetchNameReplaceJoke(String fullName)
            throws NonSplittableNameException {
    }

    @Override
    public void onSuccessBatch(List<JokeEntry> batch) {
    }

    @Override
    public void onSuccessSingle(JokeEntry joke) {
        String jokeTitle = String.format(
                resourceHelper.getString(R.string.generic_dialog_title),
                String.valueOf(joke.getId()));

        SpannableString titleSpan = new SpannableString(jokeTitle);
        titleSpan.setSpan(new StyleSpan(Typeface.BOLD), jokeTitle.length()
                - String.valueOf(joke.getId()).length(), jokeTitle.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        view.onJokeLoaded(titleSpan,
                uiHelper.convertStringFromHtml(joke.getJoke()));
    }

    @Override
    public void onFail(String error) {
        view.onError(error);
    }
}
