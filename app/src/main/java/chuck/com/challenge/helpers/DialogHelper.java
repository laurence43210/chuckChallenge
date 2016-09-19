package chuck.com.challenge.helpers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import chuck.com.challenge.R;
import chuck.com.challenge.responsePojo.JokeEntry;

/**
 * Created by Laurence on 17/09/2016.

 * Helper class that Returns an Dialog object that can then be shown on the screen.
 */
public class DialogHelper {

    /**
     * Returns an overloaded basic dialog generic dialog showing a generic error message.
     * @return overloaded basic dialog
     */

    public static Dialog getErrorDialog(Context context) {

        return getDialogWithOkButton(
                context,
                ResourceHelper.getString(R.string.generic_title_dialog_error),
                ResourceHelper.getString(R.string.generic_message_dialog_error),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

    }

    /**
     * Returns an overloaded basic dialog generic dialog showing a joke and a customisable title.
     *
     * @param  joke  the joke object associated with the joke to
     *               display, this should contain joke text and a reference to the joke number.
     * @return overloaded basic dialog
     */

    public static Dialog getSuccessDialog(Context context, JokeEntry joke) {

        return getDialogWithOkButton(context, String.format(
                ResourceHelper.getString(R.string.generic_dialog_title),
                String.valueOf(joke.getId())),
                UIHelper.convertStringFromHtml(joke.getJoke()),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
    }

    /**
     * Returns a basic dialog with a ok button and listener attached. Message and title are customisable.
     * @param  title  dialog title
     * @param  message dialog message
     * @param  onClickListener new onClickListener to be attached to the dialog
     * @return basic dialog
     */

    private static Dialog getDialogWithOkButton(Context context, String title,
            String message, DialogInterface.OnClickListener onClickListener) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton(
                ResourceHelper.getString(android.R.string.ok), onClickListener);

        return alertDialogBuilder.create();

    }

}
