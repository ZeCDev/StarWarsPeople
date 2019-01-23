package com.zecdev.starwarspeople.controller

class Config {

    companion object{

        /**
         * The base URL from the server.
         */
        const val BASE_API_URL = "https://swapi.co/api/"

        /**
         * The URL to obtain all vehicles.
         */
        const val VEHICLES_URL = "$BASE_API_URL" + "vehicles/"

        /**
         * The URL to obtain all characters.
         */
        const val CHARACTER_URL = "$BASE_API_URL" + "people/"

        /**
         * The URL to obtain all species.
         */
        const val SPECIE_URL = "$BASE_API_URL" + "specie/"
    }
}