package com.zecdev.starwarspeople.controller

import com.zecdev.starwarspeople.model.ModelType

/**
 * This interface have the events that can be
 * happening on HttpRequest.
 */
interface HttpRequestCallback {

    /**
     * This function indicates that some data was received
     * from the server.
     * @param data The data received from the server.
     * @param type Is the type of model received.
     */
    fun onDataReceived(data: String, type: ModelType);

    /**
     * This function indicates that a some fail happens during
     * the request to the server.
     * @param error The error that has the reason why it failed.
     * @param type Is the type of model that failed.
     */
    fun onDataFailedReceiving(error: Error, type: ModelType)
}
