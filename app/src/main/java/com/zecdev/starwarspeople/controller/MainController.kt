package com.zecdev.starwarspeople.controller
import com.zecdev.starwarspeople.model.Character
import com.zecdev.starwarspeople.model.ModelType
import com.zecdev.starwarspeople.model.ParserUtils
import java.lang.Error

/**
 * This class abstract the requests that will be
 * asked to the server.
 */
class MainController constructor(): HttpRequestCallback {

    private var httpRequest : HttpRequest
    private var callback : MainControllerCallback?
    private var characterStore: CharacterStore

    init {
        this.httpRequest = HttpRequest(this)
        this.callback = null
        this.characterStore = CharacterStore()
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
            when {
                instance.characterStore.speciesLoadStatus == LoadStatus.IDLE -> {
                    instance.requestSpecies()
                }
                instance.characterStore.speciesLoadStatus == LoadStatus.LOADING -> {
                    //wait
                }
                else -> {
                    instance.requestCharacters()
                }
            }
        }

        /**
         * This function start loading vehicles from the server.
         * When a request is completed the onCharacterVehiclesLoad will
         * be called.
         */
        fun loadVehicles() {
            instance.requestVehicles()
        }

        fun setDelegate(callback : MainControllerCallback)
        {
            instance.callback = callback
        }
    }

    private fun requestSpecies()
    {
        if(characterStore.speciesLoadStatus == LoadStatus.LOADED){
            Log.d("All species items are loaded")
            requestCharacters()
            return;
        }
        val nextPage = this.characterStore.specieLastPagination + 1
        val url = Config.SPECIES_URL + Config.SPECIFIC_PAGE_URL + nextPage.toString()
        instance.httpRequest.request(url, ModelType.SPECIES);
    }

    private fun requestCharacters()
    {
        if(characterStore.charactersLoadStatus == LoadStatus.LOADED){
            Log.d("All characters items are loaded")
            return;
        }

        val nextPage = this.characterStore.characterLastPagination + 1
        val url = Config.CHARACTERS_URL + Config.SPECIFIC_PAGE_URL + nextPage.toString()
        instance.httpRequest.request(url, ModelType.CHARACTERS);
    }

    private fun requestVehicles()
    {
        if(characterStore.vehiclesLoadStatus == LoadStatus.LOADED){
            Log.d("All vehicles items are loaded")
            return;
        }

        val nextPage = this.characterStore.vehicleLastPagination + 1
        val url = Config.VEHICLES_URL + Config.SPECIFIC_PAGE_URL + nextPage.toString()
        instance.httpRequest.request(url, ModelType.VEHICLES);
    }

    private fun charactersDataReceived(data: String)
    {
        this.characterStore.addCharacters(data)
        Log.d("New characters available")

        val charactersArray = ArrayList(characterStore.characters.values)
        this.callback?.onCharactersLoad(charactersArray)
    }

    private fun vehiclesDataReceived(data: String)
    {
        this.characterStore.addVehicles(data)
        Log.d("New vehicles received")

        if(ParserUtils.hasNext(data)){
            requestVehicles()
        }
        else
        {
            Log.d("All vehicles are loaded")
            val charactersArray = ArrayList(characterStore.characters.values)
            this.callback?.onCharacterVehiclesLoad(charactersArray)
        }
    }

    private fun speciesDataReceived(data: String)
    {
        this.characterStore.addSpecies(data);

        if(ParserUtils.hasNext(data)){
            requestSpecies()
        }
        else
        {
            Log.d("All species are loaded")
            requestCharacters()
        }
    }

    override fun onDataReceived(data: String, type: ModelType) {
        Log.d(object{}.javaClass.enclosingMethod.name)

        when (type) {
            ModelType.CHARACTERS -> charactersDataReceived(data);
            ModelType.VEHICLES -> vehiclesDataReceived(data);
            ModelType.SPECIES -> speciesDataReceived(data);
        }
    }

    override fun onDataFailedReceiving(error: Error, type: ModelType) {
        Log.d(object{}.javaClass.enclosingMethod.name + error.message)

        when (type) {
            ModelType.CHARACTERS -> this.callback?.onCharactersFailedLoading(error)
            ModelType.VEHICLES -> this.callback?.onCharacterVehiclesFailedLoading(error)
            ModelType.SPECIES -> this.callback?.onCharactersFailedLoading(error)
        }
    }
}