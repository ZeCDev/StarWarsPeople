package com.zecdev.starwarspeople

import com.zecdev.starwarspeople.model.Character

interface OnClickListener {
    /**
     * This function indicates that a click happens.
     * @param character The character that was clicked
     */
    fun onClick(character: Character)
}