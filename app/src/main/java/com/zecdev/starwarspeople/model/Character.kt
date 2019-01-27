package com.zecdev.starwarspeople.model

import com.zecdev.starwarspeople.controller.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class Character constructor(id: Int, name: String, specieId: Int,
                            vehiclesUrls: List<String>, gender: String,
                            homeWorld: String, skinColor: String){

    val id : Int
    val name : String
    val specieId : Int
    var specie : Specie?
    val vehiclesUrls : List<String>
    var vehicles : ArrayList<Vehicle>
    val gender : String
    val homeWorld : String
    val skinColor : String

    init {
        this.id = id
        this.name = name
        this.specieId = specieId
        this.specie = null
        this.vehiclesUrls = vehiclesUrls
        this.vehicles = ArrayList()
        this.gender = gender
        this.homeWorld = homeWorld
        this.skinColor = skinColor
    }

    /**
     * This function return a list of id's of vehicles,
     * that this character is owner.
     * @return a list of vehicles id's
     */
    fun getVehiclesIds(): List<Int> {
        var ids = ArrayList<Int>()
        for (item: String in vehiclesUrls) {
            val vehiclesIdsSplit: List<String> = item.split("/")
            val id: Int = vehiclesIdsSplit.get(vehiclesIdsSplit.size - 2).toInt()
            ids.add(id)
        }
        return ids
    }

    companion object {

        /**
         * Parse the data received from the server.
         * @see https://swapi.co/documentation
         * @param data The data in JSON format with a list
         * of characters.
         * @return a map with characters. <CharacterId, Character>
         */
        fun unarchive(data: String) : HashMap<Int, Character> {
            Log.d(object{}.javaClass.enclosingMethod.name)

            var map : HashMap<Int, Character> = HashMap<Int, Character>()

            if(data == null){
                return map;
            }

            //Parse the data
            val jsonObject = JSONObject(data)
            val results = jsonObject.getJSONArray("results")

            for (i in 0..(results.length() - 1)) {

                try {
                    val character = results.getJSONObject(i)

                    val url = character.get("url") as String
                    //The type is https://swapi.co/api/people/1/
                    val lstValues: List<String> = url.split("/")
                    val id: Int = lstValues.get(lstValues.size - 2).toInt()

                    val name = character.get("name") as String

                    val speciesArray = character.get("species") as JSONArray
                    //Consider only one specie
                    val firstSpecie = speciesArray.get(0) as String
                    val firstSpecieValues: List<String> = firstSpecie.split("/")
                    val specieId: Int = firstSpecieValues.get(firstSpecieValues.size - 2).toInt()

                    val vehiclesArrayJSON = character.get("vehicles") as JSONArray
                    var vehiclesArray = ArrayList<String>()
                    for (i in 0..(vehiclesArrayJSON.length() - 1)) {
                        vehiclesArray.add(vehiclesArrayJSON.get(i) as String)
                    }

                    val gender = character.get("gender") as String
                    val homeWorld = character.get("homeworld") as String
                    val skinColor = character.get("skin_color") as String

                    val c: Character = Character(id, name, specieId, vehiclesArray, gender, homeWorld, skinColor)
                    map.put(id, c);
                }
                catch (e: JSONException) { null }
            }

            return map;
        }
    }

}