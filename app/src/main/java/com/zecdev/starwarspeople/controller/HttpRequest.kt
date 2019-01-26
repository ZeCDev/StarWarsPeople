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

    /**
     * This function make a async request to a specific URL.
     * If the event have success the onDataReceived will be called,
     * otherwise the onDataFailedReceiving will be called.
     * @param url The url to make the request.
     * @param type The type of the request, that represent the request.
     */
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