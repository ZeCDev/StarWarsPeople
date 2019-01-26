package com.zecdev.starwarspeople.controller

import com.zecdev.starwarspeople.model.Character
import com.zecdev.starwarspeople.model.ParserUtils
import com.zecdev.starwarspeople.model.Specie
import com.zecdev.starwarspeople.model.Vehicle

public enum class LoadStatus {
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

        characters.putAll(charactersMap);

        updateVehiclesInCharacters()

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
            updateVehiclesInCharacters()
        }
    }

    /**
     * This function update the vehicles in each characters.
     * It can be used when the list of vehicles change or when
     * the list it's successful loaded.
     */
    private fun updateVehiclesInCharacters()
    {
        Log.d(object{}.javaClass.enclosingMethod.name)
        if(vehiclesLoadStatus != LoadStatus.LOADED){
            //wait until all vehicles are loaded
            return;
        }

        for ((key, value) in this.characters) {
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