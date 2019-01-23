package com.zecdev.starwarspeople.controller
import com.zecdev.starwarspeople.model.Character

class MainController constructor() {

    private var characters : List<Character>
    private var httpRequest : HttpRequest

    init {
        this.characters = ArrayList<Character>()
        this.httpRequest = HttpRequest()
    }

    /**
     * This function start loading characters from the server.
     * When a request is completed the onCharactersLoad will be
     * called.
     */
    fun loadCharacters() {

    }

    /**
     * This function start loading vehicles from the server.
     * When a request is completed the onCharacterVehiclesLoad will
     * be called.
     * @param character The character whom that the vehicles
     * will be loaded.
     */
    fun loadVehicles(character: Character) {

    }
}