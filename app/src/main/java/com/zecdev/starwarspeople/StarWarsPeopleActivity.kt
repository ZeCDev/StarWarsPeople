package com.zecdev.starwarspeople

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.zecdev.starwarspeople.controller.Log
import com.zecdev.starwarspeople.controller.MainController
import com.zecdev.starwarspeople.controller.MainControllerCallback
import com.zecdev.starwarspeople.model.Character
import java.lang.Error

class StarWarsPeopleActivity : AppCompatActivity(), MainControllerCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_wars_people)

        getBtnAbout().setOnClickListener {
            val intent = Intent(applicationContext, AboutActivity::class.java)
            startActivity(intent);
        }

        //Subscribe the events
        MainController.setDelegate(this)
        //Start loading characters
        //MainController.loadCharacters()
    }

    private fun getBtnAbout():Button{
        return findViewById(com.zecdev.starwarspeople.R.id.btnAbout) as android.widget.Button
    }

    private fun getCharactersRecyclerView():RecyclerView{
        return findViewById(R.id.charactersRecyclerView) as RecyclerView
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
