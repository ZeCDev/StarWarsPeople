package com.zecdev.starwarspeople.controller

import com.zecdev.starwarspeople.model.Character
import com.zecdev.starwarspeople.model.Vehicle
import java.lang.Error

/**
 * This interface have the events that can be
 * happening on MainController.
 */
interface MainControllerCharactersCallback {

    /**
     * This function indicates that a load of characters from the
     * server happens.
     * @param characters The list of the characters received.
     */
    fun onCharactersLoad(characters: List<Character>)

    /**
     * This function indicates that a load of characters from the
     * server failed.
     * @param error The error that has the reason why it failed
     */
    fun onCharactersFailedLoading(error: Error)
}

