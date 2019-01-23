package com.zecdev.starwarspeople.controller
import com.zecdev.starwarspeople.model.Character

/**
 * This class abstract the requests that will be
 * asked to the server.
 */
class MainController : HttpRequestCallback {

    private var characters : List<Character>
    private var httpRequest : HttpRequest

    init {
        this.characters = ArrayList<Character>()
        this.httpRequest = HttpRequest()
    }

    companion object {

        //Singleton
        private val instance = MainController()

        /**
         * This function start loading characters from the server.
         * When a request is completed the onCharactersLoad will be
         * called.
         */
        fun loadCharacters() {
            //instance.httpRequest.request();
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


    override fun onDataReceived() {
        //Parse the data
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataFailedReceiving(error: Error) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}