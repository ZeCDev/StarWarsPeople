package com.zecdev.starwarspeople.model

import java.lang.Exception

class Vehicle constructor(id: Int, name: String, model: String,
                          manufacturer: String) {
    private val id : Int
    private val name : String
    private val model : String
    private val manufacturer : String

    init {
        this.id = id
        this.name = name
        this.model = model
        this.manufacturer = manufacturer
    }

    companion object {

        fun unarchive(data: Array<Byte>) : List<Vehicle> {

            var list : ArrayList<Vehicle> = ArrayList<Vehicle>()

            if(data == null){
                return list;
            }

            //Parse the data

            return list;
        }
    }
}