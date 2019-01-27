package com.zecdev.starwarspeople

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import java.lang.Error

class UIUtils {

    companion object {
        /**
         * Show a alertDialog to user with the error that happens.
         * @param error The error with the reason for the failure.
         */
        public fun showAlert(activity: AppCompatActivity, error: Error)
        {
            activity.runOnUiThread {
                val builder = AlertDialog.Builder(activity)
                builder.setTitle("Something went wrong")
                builder.setMessage("An error happened. Description : " + error.message)

                builder.setPositiveButton("Ok"){dialog, which ->
                    dialog.dismiss()
                }

                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }
    }
}