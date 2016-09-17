package chuck.com.challenge.activities.baseActivity;

import android.content.Context;
import android.support.v4.app.Fragment;

import chuck.com.challenge.listeners.GlobalListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class BaseFragment extends Fragment {

    protected static final String TAG = BaseFragment.class.getSimpleName();

    protected GlobalListener mListener;

    public BaseFragment() {
    }

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

}
