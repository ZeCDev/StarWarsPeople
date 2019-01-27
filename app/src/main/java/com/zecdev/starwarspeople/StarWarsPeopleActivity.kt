package com.zecdev.starwarspeople

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.zecdev.starwarspeople.controller.Log
import com.zecdev.starwarspeople.controller.MainController
import com.zecdev.starwarspeople.controller.MainControllerCharactersCallback
import com.zecdev.starwarspeople.model.Character
import java.lang.Error

class StarWarsPeopleActivity : AppCompatActivity(), MainControllerCharactersCallback, OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_star_wars_people)

        getBtnAbout().setOnClickListener {
            val intent = Intent(applicationContext, AboutActivity::class.java)
            startActivity(intent);
        }

        createCharacterRecyclerView()
        setScrollListener()

        //Subscribe the events
        MainController.setCharactersDelegate(this)
        //Start loading characters
        MainController.loadCharacters()
    }

    /**
     * Initialize the recyclerView. Change the adapter of
     * the recyclerView to CharactersRecyclerAdapter and
     * the layoutManager to a new LinearLayoutManager.
     */
    private fun createCharacterRecyclerView()
    {
        getCharactersRecyclerView().adapter = CharactersRecyclerAdapter(this)
        getCharactersRecyclerView().layoutManager = LinearLayoutManager(this)
    }

    /**
     * Start listening the scroll events on RecyclerView.
     */
    private fun setScrollListener()
    {
        getCharactersRecyclerView().addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (recyclerView != null && !recyclerView.canScrollVertically(1)) {
                    MainController.loadCharacters()
                }
            }
        })
    }

    private fun getBtnAbout():Button{
        return findViewById(com.zecdev.starwarspeople.R.id.btnAbout) as android.widget.Button
    }

    private fun getCharactersRecyclerView():RecyclerView{
        return findViewById(R.id.charactersRecyclerView) as RecyclerView
    }

    /**
     * @see OnClickListener.onClick
     */
    override fun onClick(character: Character) {
        Log.d("Clicked name = " + character.name)

        val intent = Intent(applicationContext, CharacterActivity::class.java)
        intent.putExtra("characterId", character.id)
        startActivity(intent);
    }

    /**
     * @see MainControllerCallback.onCharactersLoad
     */
    override fun onCharactersLoad(characters: List<Character>) {
        Log.d(object{}.javaClass.enclosingMethod.name + " size = " + characters.size)

        runOnUiThread {
            var adapter: CharactersRecyclerAdapter = getCharactersRecyclerView().adapter as CharactersRecyclerAdapter;
            adapter.characters = ArrayList(characters)
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * @see MainControllerCallback.onCharactersFailedLoading
     */
    override fun onCharactersFailedLoading(error: Error) {
        Log.d(object{}.javaClass.enclosingMethod.name + " onCharactersFailedLoading");
        UIUtils.showAlert(this, error)
    }
}
