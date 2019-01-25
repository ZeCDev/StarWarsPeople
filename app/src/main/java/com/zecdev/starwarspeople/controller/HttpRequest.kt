package com.zecdev.starwarspeople.controller

import com.github.kittinunf.fuel.Fuel
import com.zecdev.starwarspeople.model.ModelType

/**
 * This class it's responsible to management
 * requests to Server.
 */
class HttpRequest constructor(callback: HttpRequestCallback) {

    private var callback : HttpRequestCallback

    init {
        this.callback = callback
    }

    fun request(url : String, type : ModelType) {
        Log.d("Request to url: " + url)
        Fuel.get(url).responseString { request, response, result ->

            result.fold({ data ->
                callback.onDataReceived(data, type)
            }, { err ->
                val error = Error(err.message)
                callback.onDataFailedReceiving(error, type)
            })
        }
    }
}