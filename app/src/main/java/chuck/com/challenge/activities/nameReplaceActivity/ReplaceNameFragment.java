package chuck.com.challenge.activities.nameReplaceActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import chuck.com.challenge.R;
import chuck.com.challenge.activities.baseActivity.BaseFragment;
import chuck.com.challenge.responsePojo.JokeEntry;
import chuck.com.challenge.responsePojo.ResponseParent;
import chuck.com.challenge.appEnums.ContentValuesEnum;
import chuck.com.challenge.appEnums.ServerCallEnum;
import chuck.com.challenge.exceptions.NonSplittableNameException;
import chuck.com.challenge.helpers.DialogHelper;
import chuck.com.challenge.helpers.RegexHelper;
import chuck.com.challenge.helpers.ResourceHelper;
import chuck.com.challenge.helpers.SharedPreferencesHelper;
import chuck.com.challenge.helpers.VolleyHelper;
import chuck.com.challenge.appListeners.OnOneOffClickListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class ReplaceNameFragment extends BaseFragment {

    View view;

    @BindView(R.id.submitButton)
    Button submitButton;

    @BindView(R.id.input_layout_name)
    TextInputLayout textInputLayout;

    @BindView(R.id.input_name)
    TextInputEditText textInput;

    public ReplaceNameFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_name_replace, container,
                false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        textInput.setSaveEnabled(true);
        setSubmitButtonStatus(false);
        textInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                    int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                    int count) {
                setSubmitButtonStatus(RegexHelper.isValidName(s.toString()));
                setEditTextErrorStatus(RegexHelper.isValidName(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        submitButton.setOnClickListener(new OnOneOffClickListener() {
            @Override
            public void onOneClick(View v) {
                checkTextAndSubmit();
            }
        });

    }

    private void setSubmitButtonStatus(boolean stringIsValid) {
        submitButton.setBackgroundColor(ResourceHelper
                .getColor(stringIsValid ? R.color.colorAccent
                        : R.color.colorAccent_disabled));
        textInputLayout.setErrorEnabled(false);
    }

    private void setEditTextErrorStatus(boolean stringIsValid) {
        textInputLayout.setErrorEnabled(stringIsValid);
    }

    private void checkTextAndSubmit() {
        if (RegexHelper.isValidName(textInput.getText().toString())) {
            hideKeyboard();

            ContentValues contentValues = new ContentValues();
            try {
                contentValues.put(ContentValuesEnum.FIRST_NAME.getKey(),
                        RegexHelper.splitNameString(textInput.getText()
                                .toString(), true));
                contentValues.put(ContentValuesEnum.LAST_NAME.getKey(),
                        RegexHelper.splitNameString(textInput.getText()
                                .toString(), false));
                contentValues.put(ContentValuesEnum.RESTRICT_EXPLICIT.getKey(),
                        SharedPreferencesHelper.isNonExplicitsEnabled());

            } catch (NonSplittableNameException e) {
                textInputLayout
                        .setError(getString(R.string.name_replace_error_message_unsplittable_name));
                return;
            }

            VolleyHelper.makeVolleyCall(ServerCallEnum.NAME_REPLACE,
                    contentValues, new Response.Listener<ResponseParent>() {
                        @Override
                        public void onResponse(ResponseParent response) {

                            if (response.getValues() != null
                                    && !response.getValues().isEmpty()) {
                                JokeEntry joke = response.getValues().get(0);

                                DialogHelper.getSuccessDialog(getActivity(),
                                        joke).show();

                            } else {
                                DialogHelper.getErrorDialog(getActivity())
                                        .show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            DialogHelper.getErrorDialog(getActivity()).show();
                        }
                    });
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
