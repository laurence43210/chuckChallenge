package chuck.com.challenge.extensions

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.text.SpannableString
import chuck.com.challenge.R

fun Context.getErrorDialog(errorMessage: String) =
        getDialogWithOkButton(SpannableString.valueOf(getString(R.string.title_dialog_error)),
                errorMessage)

fun Context.getDialogWithOkButton(title: SpannableString, message: CharSequence): Dialog =
        AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(getString(android.R.string.ok)) { dialog, _ -> dialog.dismiss() }
                .create()
