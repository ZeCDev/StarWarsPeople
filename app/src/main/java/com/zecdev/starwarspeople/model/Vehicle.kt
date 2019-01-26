package com.zecdev.starwarspeople.model

import org.json.JSONException
import org.json.JSONObject

class Vehicle constructor(id: Int, name: String, model: String,
                          manufacturer: String) {
    val id : Int
    val name : String
    val model : String
    val manufacturer : String

    init {
        this.id = id
        this.name = name
        this.model = model
        this.manufacturer = manufacturer
    }

    companion object {

        /**
         * Parse the data received from the server.
         * @see https://swapi.co/documentation
         * @param data The data in JSON format with a list
         * of vehicles.
         * @return a map with vehicles. <VehicleId, Vehicle>
         */
        fun unarchive(data: String) : HashMap<Int, Vehicle> {

            var map : HashMap<Int, Vehicle> = HashMap<Int, Vehicle>()

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
                    val model = character.get("model") as String
                    val manufacturer = character.get("manufacturer") as String

                    val v: Vehicle = Vehicle(id, name, model, manufacturer)
                    map.put(id, v);
                }
                catch (e: JSONException) { null }
            }
            return map;
        }
    }
}