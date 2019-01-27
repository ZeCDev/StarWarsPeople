package com.zecdev.starwarspeople

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.support.annotation.UiThread
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.TextView
import com.zecdev.starwarspeople.controller.Log
import com.zecdev.starwarspeople.controller.MainController
import com.zecdev.starwarspeople.controller.MainControllerHomeWorldCallback
import com.zecdev.starwarspeople.controller.MainControllerVehiclesCallback
import com.zecdev.starwarspeople.model.Character
import com.zecdev.starwarspeople.model.HomeWorld
import java.lang.Error
import java.net.URLEncoder


class CharacterActivity : AppCompatActivity(), MainControllerVehiclesCallback, MainControllerHomeWorldCallback {

    private var character: Character? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        val mIntent = intent
        val characterId = mIntent.getIntExtra("characterId", 0)

        Log.d("CharacterId = " + characterId)

        this.character = getCharactersById(characterId)
        if(character == null){
            Log.d("Something went wrong")
            return;
        }

        createVehicleRecyclerView()
        MainController.setHomeWorldDelegate(this)

        getNameTextView().text = character!!.name
        getGenderTextView().text = character!!.gender

        val hw = MainController.getHomeWorld(character!!.homeWorld)
        getHomeWorldTextView().text = "Loading..."
        if(hw != null){
            getHomeWorldTextView().text = hw.name
        }

        getSkinColorTextView().text = character!!.skinColor
        getSkinColorTextView().setTextColor(Color.parseColor(UIUtils.getColor(character!!.skinColor)))

        getBtnSearch().setOnClickListener {
            googleSearch()
        }

        MainController.setVehiclesDelegate(this)

        if(this.character!!.vehicles.size == 0)
        {
            MainController.loadVehicles()
        }
        else
        {
            onCharacterVehiclesLoad(MainController.getCharacters())
        }
    }

    /**
     * This function does a search on google by this character
     */
    private fun googleSearch()
    {
        if(character == null){
            return
        }

        val escapedQuery = URLEncoder.encode(character!!.name, "UTF-8")
        val uri = Uri.parse("http://www.google.com/#q=$escapedQuery")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    /**
     * Initialize the recyclerView. Change the adapter of
     * the recyclerView to VehiclesRecyclerAdapter and
     * the layoutManager to a new LinearLayoutManager.
     */
    private fun createVehicleRecyclerView()
    {
        if(this.character == null){
            return;
        }

        getVehiclesRecyclerView().adapter = VehiclesRecyclerAdapter(this.character!!)
        getVehiclesRecyclerView().layoutManager = LinearLayoutManager(this)
    }

    private fun getBtnSearch(): Button {
        return findViewById(com.zecdev.starwarspeople.R.id.search_btn) as android.widget.Button
    }

    private fun getNameTextView(): TextView {
        return findViewById(com.zecdev.starwarspeople.R.id.characterName) as android.widget.TextView
    }

    private fun getGenderTextView(): TextView {
        return findViewById(com.zecdev.starwarspeople.R.id.characterGender) as android.widget.TextView
    }

    private fun getHomeWorldTextView(): TextView {
        return findViewById(com.zecdev.starwarspeople.R.id.characterHomeWorld) as android.widget.TextView
    }

    private fun getSkinColorTextView(): TextView {
        return findViewById(com.zecdev.starwarspeople.R.id.characterSkinColor) as android.widget.TextView
    }

    private fun getVehiclesRecyclerView(): RecyclerView {
        return findViewById(R.id.vehiclesRecyclerView) as RecyclerView
    }

    private fun getCharactersById(characterId: Int): Character? {
        val characters = MainController.getCharacters();
        for(item in characters)
        {
            if(item.id == characterId){
                return item
            }
        }

        return null
    }

    /**
     * @see MainControllerVehiclesCallback.onCharacterVehiclesLoad
     */
    override fun onCharacterVehiclesLoad(characters: List<Character>) {
        Log.d(object{}.javaClass.enclosingMethod.name)

        runOnUiThread {
            var adapter: VehiclesRecyclerAdapter = getVehiclesRecyclerView().adapter as VehiclesRecyclerAdapter;

            if(character != null) {
                val characterAux = getCharactersById(character!!.id)
                if (characterAux != null) {
                    adapter.vehicles = characterAux.vehicles
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    /**
     * @see MainControllerVehiclesCallback.onCharacterVehiclesFailedLoading
     */
    override fun onCharacterVehiclesFailedLoading(error: Error) {
        Log.d(object{}.javaClass.enclosingMethod.name + " onCharactersFailedLoading");
        UIUtils.showAlert(this, error)
    }

    /**
     * @see MainControllerHomeWorldCallback.onHomeWorldLoad
     */
    override fun onHomeWorldLoad(homeWorld: HomeWorld) {
        runOnUiThread{
            getHomeWorldTextView().text = homeWorld.name
        }
    }
}
