package chuck.com.challenge.activities.baseActivity;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import chuck.com.challenge.appListeners.GlobalListener;
import dagger.android.support.DaggerFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public abstract class BaseFragment extends DaggerFragment {

    protected static final String TAG = BaseFragment.class.getSimpleName();

    protected GlobalListener mListener;

    protected View view;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GlobalListener) {
            mListener = (GlobalListener) context;
        } else {
            throw new RuntimeException((new StringBuilder())
                    .append(context.toString()).append(TAG)
                    .append(" must implement Listeners").toString());
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        butterKnifeBind();
        initUI();
    }

    abstract protected void butterKnifeBind();

    abstract protected void initUI();
}
