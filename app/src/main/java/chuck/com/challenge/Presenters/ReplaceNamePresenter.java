package chuck.com.challenge.Presenters;

import java.util.List;

import javax.inject.Inject;

import android.graphics.Typeface;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.StyleSpan;

import chuck.com.challenge.ChuckChallengeApplication;
import chuck.com.challenge.R;
import chuck.com.challenge.Models.AsyncJokeRetriever;
import chuck.com.challenge.appListeners.IReplaceNamePresenter;
import chuck.com.challenge.appListeners.IReplaceNameView;
import chuck.com.challenge.appListeners.OnJokeRetrievedListener;
import chuck.com.challenge.exceptions.NonSplittableNameException;
import chuck.com.challenge.helpers.RegexHelper;
import chuck.com.challenge.helpers.ResourceHelper;
import chuck.com.challenge.helpers.UIHelper;
import chuck.com.challenge.responsePojo.JokeEntry;

/**
 * Created by Laurence on 12/10/2016.
 */
public class ReplaceNamePresenter implements TextWatcher,
        IReplaceNamePresenter, OnJokeRetrievedListener {


    private ResourceHelper resourceHelper;

    private UIHelper uiHelper;

    private RegexHelper regexHelper;

    private IReplaceNameView view;

    private AsyncJokeRetriever model;

    public ReplaceNamePresenter(IReplaceNameView view, AsyncJokeRetriever model, UIHelper uiHelper, ResourceHelper resourceHelper, RegexHelper regexHelper) {
        this.view = view;
        this.model = model;
        this.resourceHelper = resourceHelper;
        this.uiHelper = uiHelper;
        this.regexHelper = regexHelper;
    }

    @Override
    public void fetchSingleRandomJoke() {
    }

    @Override
    public void fetchNameReplaceJoke(String fullName)
            throws NonSplittableNameException {

        model.RetrieveNameReplaceJoke(this,
                regexHelper.splitNameString(fullName, true),
                regexHelper.splitNameString(fullName, false));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
            int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        setSubmitButtonStatus(regexHelper.isValidName(s.toString()));
        setEditTextErrorStatus(regexHelper.isValidName(s.toString()));
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void setSubmitButtonStatus(boolean stringIsValid) {
        view.onSubmitButtonBackgroundChange(stringIsValid ? R.drawable.button
                : R.drawable.button_faded);
    }

    private void setEditTextErrorStatus(boolean stringIsValid) {
        view.onRemoveTextInputLayoutErrorState(stringIsValid);
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

    @Override
    public void onSetButtonDrawableToFaded() {
        setSubmitButtonStatus(false);
    }
}
