package com.zecdev.starwarspeople.controller

import com.zecdev.starwarspeople.model.Character
import java.lang.Error

interface MainControllerVehiclesCallback {

    /**
     * This function indicates that a load of vehicles from the
     * server happens.
     * @param characters The list of the characters available
     * with all cars.
     */
    fun onCharacterVehiclesLoad(characters: List<Character>)

    /**
     * This function indicates that a load of vehicles from the
     * server failed.
     * @param error The error that has the reason why it failed.
     */
    fun onCharacterVehiclesFailedLoading(error: Error)
}