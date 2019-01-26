package com.zecdev.starwarspeople.controller

class Log
{
    companion object {

        private val TAG = "StarWars"

        /**
         * This function it's used for logging.
         * @param msg The msg that will be displayed on
         * logcat.
         */
        fun d(msg : String) {
            android.util.Log.d(TAG, msg)
        }
    }
}