package com.zecdev.starwarspeople.model

import org.json.JSONObject

/**
 * This class have function with utilities to parse
 * data received from the swapi API.
 */
class ParserUtils {

    companion object {

        /**
         * Indicates if a next page is available.
         * @param data The JSON data received from the server.
         * @return true if have a next page, otherwise return
         * false.
         */
        fun hasNext(data: String): Boolean {

            if(data == null){
                return false;
            }

            //Parse the data
            val jsonObject = JSONObject(data)

            if(jsonObject.isNull("next")) {
                return false;
            }

            return true;
        }

        /**
         * Indicates the page of this data.
         * @param data The JSON data received from the server.
         * @return a integer that represent the number of
         * the page. If something goes wrong, is returned -1.
         */
        fun getActualPage(data: String): Int {

            if(data == null){
                return -1;
            }

            //Parse the data
            val jsonObject = JSONObject(data)

            if(jsonObject.isNull("previous")) {
                return 1;
            }

            if(jsonObject.isNull("next")){
                //use previous to discover the current page
                val previous = jsonObject.get("previous") as String
                val nextArray = previous.split("=")
                if(previous.split("=").size != 2){
                    return -1;
                }

                val actualPage = nextArray.get(nextArray.lastIndex).toInt() + 1

                return actualPage
            }

            //use next to discover the current page
            //Example = "https://swapi.co/api/species/?page=2",
            val next = jsonObject.get("next") as String
            val nextArray = next.split("=")
            if(next.split("=").size != 2){
                return -1;
            }

            val actualPage = nextArray.get(nextArray.lastIndex).toInt() - 1

            return actualPage
        }

        /**
         * Indicates the next page of this data.
         * @param data The JSON data received from the server.
         * @return a integer that represent the number of
         * the next page. If something goes wrong, or there is no next
         * is returned -1.
         */
        fun getNextPage(data: String): Int {
            if(data == null){
                return -1;
            }

            //Parse the data
            val jsonObject = JSONObject(data)

            if(jsonObject.isNull("next")) {
                return -1;
            }

            //Example = "https://swapi.co/api/species/?page=2",
            val next = jsonObject.get("next") as String
            val nextArray = next.split("=")
            if(next.split("=").size != 2){
                return -1;
            }

            return nextArray.get(nextArray.lastIndex).toInt()
        }
    }
}