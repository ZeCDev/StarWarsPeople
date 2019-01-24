package com.zecdev.starwarspeople.controller

class Log
{
    companion object {

        private val TAG = "StarWars"

        fun d(msg : String) {
            android.util.Log.d(TAG, msg)
        }
    }
}