package chuck.com.challenge.activities.nameReplaceActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import chuck.com.challenge.R;
import chuck.com.challenge.activities.baseActivity.BaseFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class NameReplaceFragment extends BaseFragment {


    public NameReplaceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_name_replace, container,
                false);
        ButterKnife.bind(this, view);
        return view;
    }


}
