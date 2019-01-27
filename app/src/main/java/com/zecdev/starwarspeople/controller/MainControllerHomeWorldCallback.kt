package com.zecdev.starwarspeople.controller

import com.zecdev.starwarspeople.model.HomeWorld

/**
 * This interface have the events that can be
 * happening on MainController.
 */
interface MainControllerHomeWorldCallback {

    /**
     * This function indicates that a load of HomeWorld from the
     * server happens.
     * @param homeWorld The homeWorld received
     */
    fun onHomeWorldLoad(homeWorld: HomeWorld)

}
