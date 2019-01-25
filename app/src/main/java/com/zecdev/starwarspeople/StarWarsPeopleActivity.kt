package com.zecdev.starwarspeople

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zecdev.starwarspeople.controller.Log
import com.zecdev.starwarspeople.controller.MainController
import com.zecdev.starwarspeople.controller.MainControllerCallback
import com.zecdev.starwarspeople.model.Character
import com.zecdev.starwarspeople.model.Vehicle
import java.lang.Error

class StarWarsPeopleActivity : AppCompatActivity(), MainControllerCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_wars_people)

        MainController.setDelegate(this)
        MainController.loadCharacters()
    }

    override fun onCharactersLoad(characters: List<Character>) {
        Log.d(object{}.javaClass.enclosingMethod.name + " size = " + characters.size)
        MainController.loadCharacters()
    }

    override fun onCharactersFailedLoading(error: Error) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCharacterVehiclesLoad(characters: List<Character>) {
        Log.d(object{}.javaClass.enclosingMethod.name)
        Log.d(object{}.javaClass.enclosingMethod.name + " size = " + characters.size)
    }

    override fun onCharacterVehiclesFailedLoading(error: Error) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
