package com.zecdev.starwarspeople.model

import com.zecdev.starwarspeople.controller.Log
import org.json.JSONObject

class ParserUtils {

    companion object {

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