package chuck.com.challenge.ui.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;

import butterknife.ButterKnife;
import chuck.com.challenge.appListeners.GlobalListener;
import dagger.android.support.DaggerFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public abstract class BaseFragment extends DaggerFragment {

    protected static final String TAG = BaseFragment.class.getSimpleName();

    protected GlobalListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GlobalListener) {
            mListener = (GlobalListener) context;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }
}
