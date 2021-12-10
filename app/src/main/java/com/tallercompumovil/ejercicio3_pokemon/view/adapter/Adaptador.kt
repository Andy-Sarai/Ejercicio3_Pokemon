package com.tallercompumovil.ejercicio3_pokemon.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tallercompumovil.ejercicio3_pokemon.databinding.ListElementBinding
import com.tallercompumovil.ejercicio3_pokemon.model.Game
import com.tallercompumovil.ejercicio3_pokemon.model.Pokemon

class Adaptador(context: Context, games:Game, onItemListener: OnItemListener ): RecyclerView.Adapter<Adaptador.ViewHolder>() {

    private val games = games
    private val mOnItemListener = onItemListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adaptador.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = ListElementBinding.inflate(layoutInflater)

        return ViewHolder(binding, mOnItemListener)
    }

    override fun onBindViewHolder(holder: Adaptador.ViewHolder, position: Int) {
    //vincular el View Holder con la vista --> LIST ELEMENT
        //Que se va a llenar en el Text View y el Image View
        holder.bindData(games.results!![position])
        //holder.bindData(games[position])
    }

    override fun getItemCount(): Int {
    //cuantos elementos le voy a meter al Recycler View, debe ser la lista de los 151 pokemones
        //return games.size
        return games.results!!.size
    }

    interface OnItemListener{
        fun onItemClick(game: Pokemon)
        //fun onItemClick(game: Game)
    }

    class ViewHolder(binding: ListElementBinding, onItemListener: OnItemListener):RecyclerView.ViewHolder(binding.root),
        View.OnClickListener{

        //private val ivThumbnail = binding.ivThumbnail
        private val tvTitle = binding.tvTitle
        //private val context = binding.root.context
        private val onItemListener = onItemListener
        //private lateinit var game: Game
        private lateinit var game: Pokemon

        init{
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onItemListener.onItemClick(game)
        }

        fun bindData(item: Pokemon){
        //fun bindData(item: Game){
            tvTitle.text = item.name //REVISAR SI ESTÁ BIEN COLOCADA LA CLASE min 34:40 clase 26-Nov
            game = item

        }

    }


}