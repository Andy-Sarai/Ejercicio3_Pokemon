package com.tallercompumovil.ejercicio3_pokemon.model

import com.google.gson.annotations.SerializedName

//Se mapea la conexión con la lista con los 151 pokemones

class Game {

    @SerializedName("results")
    var results: List<Pokemon>? = null

}
class Pokemon{
    @SerializedName("name")
    var name: String? = null
}
//@SerializedName("url")
//var url: String? = null