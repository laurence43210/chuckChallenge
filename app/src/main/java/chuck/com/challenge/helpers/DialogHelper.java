package chuck.com.challenge.helpers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import chuck.com.challenge.R;
import chuck.com.challenge.Classes.JokeEntry;

/**
 * Created by Laurence on 17/09/2016.
 */
public class DialogHelper {

    public static Dialog getSuccessDialog(Context context, JokeEntry joke) {

        return getDialogWithOkButton(context, String.format(
                ResourceHelper.getString(R.string.generic_dialog_title),
                String.valueOf(joke.getId())), joke.getJoke(),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
    }

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
