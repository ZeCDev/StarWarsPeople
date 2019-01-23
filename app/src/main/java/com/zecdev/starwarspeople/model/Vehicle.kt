package com.zecdev.starwarspeople.model

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
}