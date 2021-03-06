package com.zecdev.starwarspeople.controller

import com.zecdev.starwarspeople.model.*

enum class LoadStatus {
    IDLE, LOADING, LOADED
}

class CharacterStore constructor()
{
    //<characterId, vehicle>
    var characters : HashMap<Int,Character>
    var characterLastPagination : Int;
    var charactersLoadStatus : LoadStatus;

    //<specieId, Name>
    private var species : HashMap<Int, Specie>
    var specieLastPagination : Int;
    var speciesLoadStatus : LoadStatus;

    //<vehicleId, vehicle>
    private var vehicles : HashMap<Int,Vehicle>
    var vehicleLastPagination : Int;
    var vehiclesLoadStatus : LoadStatus;

    private var homeWorlds : HashMap<Int, HomeWorld>

    init {
        this.characters = HashMap<Int, Character>()
        this.characterLastPagination = 0;
        this.charactersLoadStatus = LoadStatus.IDLE

        this.species = HashMap<Int, Specie>()
        this.specieLastPagination = 0;
        this.speciesLoadStatus = LoadStatus.IDLE

        this.vehicles = HashMap<Int, Vehicle>()
        this.vehicleLastPagination = 0
        this.vehiclesLoadStatus = LoadStatus.IDLE

        this.homeWorlds = HashMap<Int, HomeWorld>()
    }

    /**
     * This function store species received from the server.
     * @param data The data received from the server.
     */
    fun addSpecies(data: String)
    {
        if(this.speciesLoadStatus == LoadStatus.IDLE){
            //change status
            this.speciesLoadStatus = LoadStatus.LOADING
        }

        val speciesMap = Specie.unarchive(data);
        species.putAll(speciesMap);

        val actualPage = ParserUtils.getActualPage(data);
        Log.d("Total species = " + species.size + " current page = " + actualPage)
        specieLastPagination = actualPage;

        if(!ParserUtils.hasNext(data)){
            this.speciesLoadStatus = LoadStatus.LOADED
        }
    }

    /**
     * This function store characters received from the server.
     * @param data The data received from the server.
     */
    fun addCharacters(data: String)
    {
        if(this.charactersLoadStatus == LoadStatus.IDLE){
            this.charactersLoadStatus = LoadStatus.LOADING
        }

        val charactersMap = Character.unarchive(data);

        for ((key, value) in charactersMap) {
            //add specie to character....
            value.specie = species.get(value.specieId)
        }

        updateVehiclesInCharacters(charactersMap)

        characters.putAll(charactersMap);

        val actualPage = ParserUtils.getActualPage(data);
        Log.d("Total characters = " + characters.size + " current page = " + actualPage)
        characterLastPagination = actualPage;

        if(!ParserUtils.hasNext(data)){
            this.charactersLoadStatus = LoadStatus.LOADED
        }
    }

    /**
     * This function store vehicles received from the server.
     * @param data The data received from the server.
     */
    fun addVehicles(data: String)
    {
        if(this.vehiclesLoadStatus == LoadStatus.LOADED){
            //ignore them
            return;
        }

        if(this.vehiclesLoadStatus == LoadStatus.IDLE){
            this.vehiclesLoadStatus = LoadStatus.LOADING
        }

        val vehiclesMap = Vehicle.unarchive(data)
        vehicles.putAll(vehiclesMap)

        val actualPage = ParserUtils.getActualPage(data)
        Log.d("Total vehicles = " + vehicles.size + " current page = " + actualPage)
        this.vehicleLastPagination = actualPage;

        if(!ParserUtils.hasNext(data)){
            this.vehiclesLoadStatus = LoadStatus.LOADED
            updateVehiclesInAllCharacters()
        }
    }

    /**
     * This function store homeWorld received from the server.
     * @param data The data received from the server.
     */
    fun addHomeWorld(data: String): HomeWorld?
    {
        val homeWorld = HomeWorld.unarchive(data)
        if (homeWorld != null) {
            homeWorlds.put(homeWorld.id, homeWorld)
            return homeWorld
        }
        return null
    }

    /**
     * This function return the homeWorld with the
     * id passed by parameter.
     */
    fun getHomeWorld(homeWorldId: Int): HomeWorld?
    {
        return homeWorlds.get(homeWorldId)
    }

    /**
     * This function update the vehicles in each characters.
     * It can be used when the list of vehicles change or when
     * the list it's successful loaded.
     */
    private fun updateVehiclesInAllCharacters()
    {
        Log.d(object{}.javaClass.enclosingMethod.name)
        updateVehiclesInCharacters(this.characters)
    }

    /**
     * This function update the vehicles in characters passed by parameter.
     * @param map An map with some Characters.
     */
    private fun updateVehiclesInCharacters(map :HashMap<Int, Character>)
    {
        Log.d(object{}.javaClass.enclosingMethod.name)
        if(vehiclesLoadStatus != LoadStatus.LOADED){
            //wait until all vehicles are loaded
            return;
        }

        for ((key, value) in map) {
            //add vehicle to character....
            val listIds = value.getVehiclesIds()
            for (id: Int in listIds) {
                if(vehicles.get(id) == null){
                    //not expected
                    Log.d("Null vehicle not expected id =" + id)
                    break;
                }
                value.vehicles.add(vehicles.get(id)!!)
            }
        }
    }

}