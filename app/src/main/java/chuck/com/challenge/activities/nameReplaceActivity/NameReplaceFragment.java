package chuck.com.challenge.activities.nameReplaceActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import butterknife.OnClick;
import chuck.com.challenge.R;
import chuck.com.challenge.Classes.JokeEntry;
import chuck.com.challenge.Classes.ResponseParent;
import chuck.com.challenge.Enums.ContentValuesEnum;
import chuck.com.challenge.Enums.ServerCallEnum;
import chuck.com.challenge.activities.baseActivity.BaseFragment;
import chuck.com.challenge.helpers.DialogHelper;
import chuck.com.challenge.helpers.ResourceHelper;
import chuck.com.challenge.helpers.UIHelper;
import chuck.com.challenge.helpers.VolleyHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class NameReplaceFragment extends BaseFragment {

    //regex to look for a group of up to 20 non language specific letters,
    //then a space, then another up to 20 letters but also accepts a "-"

    private static final String NAME_REGEX = "([\\p{L}]{1,20})(\\s)([-'\\p{L}\\s]{1,20})";

    View view;

    @BindView(R.id.submitButton)
    Button submitButton;

    @BindView(R.id.input_layout_name)
    TextInputLayout textInputLayout;

    @BindView(R.id.input_name)
    TextInputEditText textInput;

    public NameReplaceFragment() {
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
                setSubmitButtonStatus(isValidName(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void setSubmitButtonStatus(boolean stringIsValid) {
        submitButton.setBackgroundColor(ResourceHelper
                .getColor(stringIsValid ? R.color.colorAccent
                        : R.color.colorAccent_disabled));

    }

    @OnClick(R.id.submitButton)
    void checkText() {
        if (isValidName(textInput.getText().toString())) {
            hideKeyboard();
            ContentValues contentValues = new ContentValues();
            contentValues.put(ContentValuesEnum.FIRST_NAME.getKey(),
                    splitNameString(textInput.getText().toString(), true));
            contentValues.put(ContentValuesEnum.LAST_NAME.getKey(),
                    splitNameString(textInput.getText().toString(), false));

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

    public boolean isValidName(String string) {
        //TODO strip out titles Mr, Miss etc...

        return !UIHelper.isStringEmptyOrNull(string)
                && Pattern.matches(NAME_REGEX, string.trim());
    }

    private String splitNameString(String string, boolean firstNameRequired) {

        Matcher matcher = Pattern.compile(NAME_REGEX).matcher(string.trim());
        while (matcher.find()) {
            String name = matcher.group(firstNameRequired ? 1 : 3);
            if (!UIHelper.isStringEmptyOrNull(name)) {
                return name;
            }
        }
        return "";
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }
}
