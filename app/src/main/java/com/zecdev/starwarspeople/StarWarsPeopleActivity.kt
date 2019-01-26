package com.zecdev.starwarspeople

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zecdev.starwarspeople.controller.Log
import com.zecdev.starwarspeople.controller.MainController
import com.zecdev.starwarspeople.controller.MainControllerCallback
import com.zecdev.starwarspeople.model.Character
import java.lang.Error

class StarWarsPeopleActivity : AppCompatActivity(), MainControllerCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_wars_people)

        //Subscribe the events
        MainController.setDelegate(this)
        //Start loading characters
        MainController.loadCharacters()
    }

    /**
     * @see MainControllerCallback.onCharactersLoad
     */
    override fun onCharactersLoad(characters: List<Character>) {
        Log.d(object{}.javaClass.enclosingMethod.name + " size = " + characters.size)
        MainController.loadCharacters()
        if(characters.size > 80){
            MainController.loadVehicles()
        }
    }

    /**
     * @see MainControllerCallback.onCharactersFailedLoading
     */
    override fun onCharactersFailedLoading(error: Error) {
        Log.d(object{}.javaClass.enclosingMethod.name + " onCharactersFailedLoading");
    }

    /**
     * @see MainControllerCallback.onCharacterVehiclesLoad
     */
    override fun onCharacterVehiclesLoad(characters: List<Character>) {
        Log.d(object{}.javaClass.enclosingMethod.name)
        Log.d(object{}.javaClass.enclosingMethod.name + " size = " + characters.size)
    }

    /**
     * @see MainControllerCallback.onCharacterVehiclesFailedLoading
     */
    override fun onCharacterVehiclesFailedLoading(error: Error) {
        Log.d(object{}.javaClass.enclosingMethod.name + " onCharactersFailedLoading");
    }
}
