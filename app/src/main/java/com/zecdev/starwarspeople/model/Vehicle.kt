package com.zecdev.starwarspeople.model

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

        fun unarchive(data: String) : HashMap<Int, Vehicle> {

            var map : HashMap<Int, Vehicle> = HashMap<Int, Vehicle>()

            if(data == null){
                return map;
            }

            //Parse the data

            return map;
        }
    }
}