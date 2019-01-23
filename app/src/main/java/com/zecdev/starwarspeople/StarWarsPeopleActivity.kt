package com.zecdev.starwarspeople

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zecdev.starwarspeople.controller.MainController
import com.zecdev.starwarspeople.controller.MainControllerCallback
import com.zecdev.starwarspeople.model.Character
import com.zecdev.starwarspeople.model.Vehicle
import java.lang.Error

class StarWarsPeopleActivity : AppCompatActivity(), MainControllerCallback {

    private var mainController : MainController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_wars_people)

        MainController.loadCharacters()
    }

    override fun onCharactersLoad(characters: List<Character>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCharactersFailedLoading(error: Error) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCharacterVehiclesLoad(character: Character, vehicles: List<Vehicle>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCharacterVehiclesFailedLoading(character: Character, error: Error) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
