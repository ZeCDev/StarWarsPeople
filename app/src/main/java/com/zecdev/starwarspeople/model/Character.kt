package com.zecdev.starwarspeople.model

import com.zecdev.starwarspeople.controller.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Character constructor(id: Int, name: String, species: String,
                            vehiclesUrls: List<String>, gender: String,
                            homeWorld: String, skinColor: String) {

    private val id : Int
    private val name : String
    private val species : String
    private val vehiclesUrls : List<String>
    private val gender : String
    private val homeWorld : String
    private val skinColor : String

    init {
        this.id = id
        this.name = name
        this.species = species
        this.vehiclesUrls = vehiclesUrls
        this.gender = gender
        this.homeWorld = homeWorld
        this.skinColor = skinColor
    }

    fun getNrVehicles(): Int {
        return vehiclesUrls.size;
    }

    companion object {

        fun unarchive(data: String) : List<Character> {
            Log.d(object{}.javaClass.enclosingMethod.name)

            var list : ArrayList<Character> = ArrayList<Character>()

            if(data == null){
                return list;
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
                    val vehiclesArrayJSON = character.get("vehicles") as JSONArray
                    var vehiclesArray = ArrayList<String>()
                    for (i in 0..(vehiclesArrayJSON.length() - 1)) {
                        vehiclesArray.add(vehiclesArrayJSON.get(i) as String)
                    }

                    val gender = character.get("gender") as String
                    val homeWorld = character.get("homeworld") as String
                    val skinColor = character.get("skin_color") as String

                    val c: Character = Character(id, name, "specie", vehiclesArray, gender, homeWorld, skinColor)
                    list.add(c)
                }
                catch (e: JSONException) { null }
            }

            return list;
        }
    }

}