package chuck.com.challenge.activities.nameReplaceActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

import butterknife.OnClick;
import chuck.com.challenge.Classes.JokeEntry;
import chuck.com.challenge.Classes.ResponseParent;
import chuck.com.challenge.Enums.ContentValuesEnum;
import chuck.com.challenge.Enums.ServerCallEnum;
import chuck.com.challenge.R;
import chuck.com.challenge.activities.baseActivity.BaseFragment;
import chuck.com.challenge.helpers.DialogHelper;
import chuck.com.challenge.helpers.UIHelper;
import chuck.com.challenge.helpers.VolleyHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class NameReplaceFragment extends BaseFragment {

    //regex to look for a group of up to 20 non language specific letters,
    //then a space, then another up to 20 letters but also accepts a "-"

    private static final String NAME_REGEX = "([\\p{L}]{1,20})(\\s)([-'\\p{L}\\s]{1,20})";

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
        View view = inflater.inflate(R.layout.fragment_name_replace, container,
                false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
    }


    public boolean isValidName(String string) {

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

}
