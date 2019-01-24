package com.zecdev.starwarspeople.controller
import com.zecdev.starwarspeople.model.Character
import com.zecdev.starwarspeople.model.ModelType
import java.lang.Error

/**
 * This class abstract the requests that will be
 * asked to the server.
 */
class MainController constructor(): HttpRequestCallback {

    private var characters : ArrayList<Character>
    private var httpRequest : HttpRequest
    private var callback : MainControllerCallback? = null
    private var characterPagination = 0;

    init {
        this.characters = ArrayList<Character>()
        this.httpRequest = HttpRequest(this)
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
            instance.httpRequest.request(Config.CHARACTER_URL, ModelType.CHARACTERS);
        }

        /**
         * This function start loading vehicles from the server.
         * When a request is completed the onCharacterVehiclesLoad will
         * be called.
         * @param character The character whom that the vehicles
         * will be loaded.
         */
        fun loadVehicles(character: Character) {
            instance.httpRequest.request(Config.VEHICLES_URL, ModelType.VEHICLES);
        }

        fun setDelegate(callback : MainControllerCallback)
        {
            instance.callback = callback
        }
    }

    private fun parseCharactersData(data: String)
    {
        //Parse the data
        var charactersReceived = Character.unarchive(data)

        if(charactersReceived.isEmpty()){
            //No characters found
            var error : Error = Error("No characters found");
            this.callback?.onCharactersFailedLoading(error)
            return;
        }

        this.characters.addAll(charactersReceived);
        this.callback?.onCharactersLoad(charactersReceived)
    }

    private fun parseVehiclesData(data: String)
    {
    }

    private fun parseSpeciesData(data: String)
    {

    }

    override fun onDataReceived(data: String, type: ModelType) {
        Log.d(object{}.javaClass.enclosingMethod.name)

        when (type) {
            ModelType.CHARACTERS -> parseCharactersData(data);
            ModelType.VEHICLES -> parseVehiclesData(data);
            ModelType.SPECIES -> parseSpeciesData(data);
        }
    }

    override fun onDataFailedReceiving(error: Error, type: ModelType) {
        Log.d(object{}.javaClass.enclosingMethod.name + error.message)
        this.callback?.onCharactersFailedLoading(error)
    }
}