package com.zecdev.starwarspeople

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zecdev.starwarspeople.controller.Log
import com.zecdev.starwarspeople.model.Character
import kotlinx.android.synthetic.main.recyclerview_character_row.view.*

class CharactersRecyclerAdapter():
    RecyclerView.Adapter<CharactersRecyclerAdapter.CharacterHolder>()  {

    var characters: ArrayList<Character>

    init
    {
        this.characters = ArrayList<Character>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val inflator = LayoutInflater.from(parent.context)
        val row = inflator.inflate(R.layout.recyclerview_character_row, parent, false)
        return CharacterHolder(row)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val itemPhoto = characters[position]
        holder.bindCharacter(itemPhoto)
    }

    class CharacterHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        private var character: Character? = null

        init {
            v.setOnClickListener(this)
        }

        fun bindCharacter(character: Character) {
            this.character = character
            var specieName = "..."
            if(character.specie != null){
                specieName = character.specie?.name!!
            }

            view.characterName.text = character.name
            view.characterSpecie.text = specieName
            view.characterVehiclesNr.text = character.vehicles.size.toString()
        }

        override fun onClick(v: View) {
            Log.d("RecyclerView Clicked")
        }

        companion object {
            private val CHARACTER_KEY = "CHARACTER"
        }
    }
}
