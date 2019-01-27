package com.zecdev.starwarspeople.model

import org.json.JSONException
import org.json.JSONObject

class HomeWorld constructor(id: Int, name: String) {
    val id : Int
    val name : String

    init {
        this.id = id
        this.name = name
    }

    companion object {

        /**
         * Parse the data received from the server.
         * @see https://swapi.co/documentation
         * @param data The data in JSON format with an HomeWorld.
         * @return an HomeWorld.
         */
        fun unarchive(data: String) : HomeWorld? {

            if(data == null){
                return null;
            }

            //Parse the data
            val jsonObject = JSONObject(data)

            try {
                val url = jsonObject.get("url") as String
                //The type is https://swapi.co/api/planets/1/
                val lstValues: List<String> = url.split("/")
                val id: Int = lstValues.get(lstValues.size - 2).toInt()

                val name = jsonObject.get("name") as String

                return HomeWorld(id, name)
            }
            catch (e: JSONException) { return null }
        }
    }
}
