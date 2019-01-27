package com.zecdev.starwarspeople

import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
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

        getCharactersRecyclerView().addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (recyclerView != null && !recyclerView.canScrollVertically(1)) {
                    MainController.loadCharacters()
                }
            }
        })

        createCharacterRecyclerView()

        //Subscribe the events
        MainController.setDelegate(this)
        //Start loading characters
        MainController.loadCharacters()
    }

    private fun createCharacterRecyclerView()
    {
        getCharactersRecyclerView().adapter = CharactersRecyclerAdapter()
        getCharactersRecyclerView().layoutManager = LinearLayoutManager(this)
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

        var adapter: CharactersRecyclerAdapter = getCharactersRecyclerView().adapter as CharactersRecyclerAdapter;
        adapter.characters = ArrayList(characters)
        adapter.notifyDataSetChanged();

        if(characters.size > 80){
            MainController.loadVehicles()
        }
    }

    private fun showAlert()
    {
        val builder = AlertDialog.Builder(this@StarWarsPeopleActivity)
        builder.setTitle("Something went wrong")
        builder.setMessage("The request to server failed. Check your internet connection.")

        builder.setPositiveButton("Ok"){dialog, which ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    /**
     * @see MainControllerCallback.onCharactersFailedLoading
     */
    override fun onCharactersFailedLoading(error: Error) {
        Log.d(object{}.javaClass.enclosingMethod.name + " onCharactersFailedLoading");
        showAlert()
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
        showAlert()
    }
}
