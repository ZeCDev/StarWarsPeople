package com.zecdev.starwarspeople.controller

/**
 * This interface have the events that can be
 * happening on HttpRequest.
 */
interface HttpRequestCallback {

    /**
     * This function indicates that some data was received
     * from the server.
     */
    abstract fun onDataReceived()

    /**
     * This function indicates that a some fail happens during
     * the request to the server.
     * @param error The error that has the reason why it failed.
     */
    abstract fun onDataFailedReceiving(error: Error)
}
