package chuck.com.challenge.appListeners;

import android.os.SystemClock;
import android.view.View;

/**
 * Created by Laurence on 19/09/2016.

 * this method will limit a click listener to ignore double clicks
 * unless they are more than a second (or whatever the custom delay is)
 * apart
 */

public abstract class OnOneOffClickListener implements View.OnClickListener {

    public OnOneOffClickListener(long customDelayInMilliseconds) {
        this.delayInMilliseconds = customDelayInMilliseconds;
    }

    public OnOneOffClickListener() {
        this.delayInMilliseconds = 1000;
    }

    private long mLastClickTime = 0;

    private long delayInMilliseconds;

    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - mLastClickTime < delayInMilliseconds) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        onOneClick(v);
    }

    public abstract void onOneClick(View v);

}
