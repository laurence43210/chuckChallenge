package chuck.com.challenge.helpers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.SpannableString;

import chuck.com.challenge.R;

/**
 * Created by Laurence on 17/09/2016.

 * Helper class that Returns an Dialog object that can then be shown on the screen.
 */
public class DialogHelper {

    private ResourceHelper resourceHelper;

    public DialogHelper(ResourceHelper resourceHelper) {
        this.resourceHelper = resourceHelper;
    }

    public Dialog getErrorDialog(Context context, String errorMessage) {

        return getDialogWithOkButton(context,
                SpannableString.valueOf(resourceHelper
                        .getString(R.string.title_dialog_error)),
                errorMessage);

    }

    public Dialog getDialogWithOkButton(Context context, SpannableString title,
                                        CharSequence message) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton(
                resourceHelper.getString(android.R.string.ok),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        return alertDialogBuilder.create();

    }

}
