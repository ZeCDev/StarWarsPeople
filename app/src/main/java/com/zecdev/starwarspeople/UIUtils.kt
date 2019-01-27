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
        fun showAlert(activity: AppCompatActivity, error: Error)
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

        /**
         * Convert a color received from the server to a Color
         * like this format:
         *
         * <ul>
         *   <li><code>#RRGGBB</code></li>
         *   <li><code>#AARRGGBB</code></li>
         * </ul>
         *
         * @param color The color received from the server.
         */
        fun getColor(color: String): String
        {
            when (color) {
                "green" -> return "#33cc33"
                "red" -> return "#ff0000"
                "fair" -> return "#ffc266"
                "gold" -> return "#ffff00"
                "white" -> return "#ffffff"
                "light" -> return "#fff2cc"
                "pale" -> return "#fff9e6"
                "metal" -> return "#c2d6d6"
                "dark" -> return "#663300"
                "brown" -> return "#994d00"
                "grey" -> return "#A2A2A2"
                else -> {
                    return "#000000"
                }
            }
        }
    }
}