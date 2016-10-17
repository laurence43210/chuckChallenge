package chuck.com.challenge.activities.nameReplaceActivity;

import javax.inject.Inject;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import chuck.com.challenge.ChuckChallengeApplication;
import chuck.com.challenge.R;
import chuck.com.challenge.Presenters.ReplaceNamePresenter;
import chuck.com.challenge.activities.baseActivity.BaseFragment;
import chuck.com.challenge.appListeners.IReplaceNameView;
import chuck.com.challenge.appListeners.OnOneOffClickListener;
import chuck.com.challenge.exceptions.NonSplittableNameException;
import chuck.com.challenge.helpers.DialogHelper;
import chuck.com.challenge.helpers.RegexHelper;
import chuck.com.challenge.helpers.ResourceHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class ReplaceNameFragment extends BaseFragment implements
        IReplaceNameView {

    @BindView(R.id.submitButton)
    Button submitButton;

    @BindView(R.id.input_layout_name)
    TextInputLayout textInputLayout;

    @BindView(R.id.input_name)
    TextInputEditText textInput;

    @Inject
    DialogHelper dialogHelper;

    @Inject
    ResourceHelper resourceHelper;

    @Inject
    RegexHelper regexHelper;

    private ReplaceNamePresenter nameReplaceSingleJokePresenter;

    @Override
    protected void daggerInjection() {
        ChuckChallengeApplication.getDiComponent().inject(this);
    }

    @Override
    protected void butterKnifeBind() {
        ButterKnife.bind(this, view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_name_replace, container,
                false);
        nameReplaceSingleJokePresenter = new ReplaceNamePresenter(this);
        return view;
    }

    protected void initUI() {
        textInputLayout.setErrorEnabled(true);
        textInput.setSaveEnabled(true);
        nameReplaceSingleJokePresenter.onSetButtonDrawableToFaded();
        textInput.addTextChangedListener(nameReplaceSingleJokePresenter);
        submitButton.setOnClickListener(new OnOneOffClickListener() {
            @Override
            public void onOneClick(View v) {
                checkTextAndSubmit();
            }
        });

    }

    @Override
    public void onJokeLoaded(SpannableString title, String joke) {
        mListener.hideProgressSpinner();
        dialogHelper.getDialogWithOkButton(getActivity(), title, joke).show();
    }

    @Override
    public void onError(String message) {
        mListener.hideProgressSpinner();
        dialogHelper.getErrorDialog(getActivity(), message).show();
    }

    @Override
    public void onSubmitButtonBackgroundChange(int drawableId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            submitButton.setBackground(resourceHelper.getDrawable(drawableId));
        } else {
            submitButton.setBackgroundDrawable(resourceHelper
                    .getDrawable(drawableId));
        }
    }

    @Override
    public void onRemoveTextInputLayoutErrorState(boolean doRemove) {
        if (doRemove) {
            textInputLayout.setError(null);
        }
    }

    private void checkTextAndSubmit() {
        if (regexHelper.isValidName(textInput.getText().toString())) {
            hideKeyboard();
            try {
                nameReplaceSingleJokePresenter.fetchNameReplaceJoke(textInput
                        .getText().toString());
                mListener.showProgressSpinner();

            } catch (NonSplittableNameException e) {
                textInputLayout
                        .setError(getString(R.string.name_replace_error_message_unsplittable_name));
            }
        } else {
            textInputLayout
                    .setError(getString(R.string.name_replace_error_message_name));
        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

}
