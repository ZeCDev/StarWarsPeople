package com.zecdev.starwarspeople.model

import org.json.JSONException
import org.json.JSONObject

class Specie constructor(id: Int, name: String, designation: String) {
    val id : Int
    val name : String
    val designation : String

    init {
        this.id = id
        this.name = name
        this.designation = designation
    }

    companion object {

        /**
         * Parse the data received from the server.
         * @see https://swapi.co/documentation
         * @param data The data in JSON format with a list
         * of species.
         * @return a map with species. <SpecieId, Specie>
         */
        fun unarchive(data: String) : HashMap<Int, Specie>  {

            var map : HashMap<Int, Specie> = HashMap<Int, Specie>()

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
                    //The type is https://swapi.co/api/species/1/
                    val lstValues: List<String> = url.split("/")
                    val id: Int = lstValues.get(lstValues.size - 2).toInt()

                    val name = character.get("name") as String
                    val designation = character.get("designation") as String

                    val s: Specie = Specie(id, name, designation)
                    map.put(id, s);
                }
                catch (e: JSONException) { null }
            }

            return map;
        }
    }
}
