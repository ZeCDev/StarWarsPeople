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

    private var charactersCallback : MainControllerCharactersCallback?
    private var vehiclesCallback : MainControllerVehiclesCallback?

    private var characterStore: CharacterStore

    init {
        this.httpRequest = HttpRequest(this)
        this.charactersCallback = null
        this.vehiclesCallback = null
        this.characterStore = CharacterStore()
    }

    companion object {

        //Singleton
        private val instance = MainController()

        /**
         * This function starts loading all the species from the server,
         * if the loading, has not yet been done, otherwise it will start
         * loading of characters from the server, starting on the last page
         * received. If not received any characters yet, then it will start
         * on first page.
         * When a request is completed the onCharactersLoad will be
         * called.
         */
        fun loadCharacters() {
            when {
                instance.characterStore.speciesLoadStatus == LoadStatus.IDLE -> {
                    instance.requestSpecies()
                }
                instance.characterStore.speciesLoadStatus == LoadStatus.LOADING -> {
                    //wait that all species are loaded.
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

        /**
         * This function it's used to define the callback.
         * The class that implement this callback and call
         * this function will be notified about the events
         * declared in the MainControllerCharactersCallback.
         * @param callback The callback that will receive the
         * events.
         */
        fun setCharactersDelegate(callback : MainControllerCharactersCallback)
        {
            instance.charactersCallback = callback
        }

        /**
         * This function it's used to define the callback.
         * The class that implement this callback and call
         * this function will be notified about the events
         * declared in the MainControllerVehiclesCallback.
         * @param callback The callback that will receive the
         * events.
         */
        fun setVehiclesDelegate(callback : MainControllerVehiclesCallback)
        {
            instance.vehiclesCallback = callback
        }

        /**
         * Return a list of characters available in memory.
         * @return the list of characters.
         */
        fun getCharacters(): ArrayList<Character> {

            var arrayCopy: ArrayList<Character> = ArrayList()
            for (character in instance.characterStore.characters.values) {
                arrayCopy.add(character)
            }

            return arrayCopy
        }
    }

    /**
     * Start a request from all species from the last page
     * received. If all species are loaded, nothing will be done.
     */
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

    /**
     * Start a request from all characters from the last page
     * received. If all characters are loaded, nothing will be done.
     */
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

    /**
     * Start a request from all vehicles from the last page
     * received. If all vehicles are loaded, nothing will be done.
     */
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

    /**
     * This function store the data about the characters received,
     * and invoke the onCharactersLoad method.
     * @param data The data received from the server.
     */
    private fun charactersDataReceived(data: String)
    {
        this.characterStore.addCharacters(data)
        Log.d("New characters available")

        val charactersArray = ArrayList(characterStore.characters.values)
        this.charactersCallback?.onCharactersLoad(charactersArray)
    }

    /**
     * This function store the data about the vehicles received,
     * and invoke the onCharacterVehiclesLoad method if all vehicles
     * are loaded, otherwise try to load the next page.
     * @param data The data received from the server.
     */
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
            this.vehiclesCallback?.onCharacterVehiclesLoad(charactersArray)
        }
    }

    /**
     * This function store the data about the species received,
     * and start request characters if all species
     * are loaded, otherwise try to load the next page.
     * @param data The data received from the server.
     */
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

    /**
     * @see HttpRequestCallback.onDataReceived
     */
    override fun onDataReceived(data: String, type: ModelType) {
        Log.d(object{}.javaClass.enclosingMethod.name)

        when (type) {
            ModelType.CHARACTERS -> charactersDataReceived(data);
            ModelType.VEHICLES -> vehiclesDataReceived(data);
            ModelType.SPECIES -> speciesDataReceived(data);
        }
    }

    /**
     * @see HttpRequestCallback.onDataFailedReceiving
     */
    override fun onDataFailedReceiving(error: Error, type: ModelType) {
        Log.d(object{}.javaClass.enclosingMethod.name + error.message)

        when (type) {
            ModelType.CHARACTERS -> this.charactersCallback?.onCharactersFailedLoading(error)
            ModelType.VEHICLES -> this.vehiclesCallback?.onCharacterVehiclesFailedLoading(error)
            ModelType.SPECIES -> this.charactersCallback?.onCharactersFailedLoading(error)
        }
    }
}