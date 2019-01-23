package com.zecdev.starwarspeople.model

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

}