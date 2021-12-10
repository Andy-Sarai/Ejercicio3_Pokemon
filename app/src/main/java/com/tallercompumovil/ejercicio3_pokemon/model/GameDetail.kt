package com.tallercompumovil.ejercicio3_pokemon.model

import com.google.gson.annotations.SerializedName

class GameDetail {  //2a Activity detalles de cada pokemón

    @SerializedName("species") //Para el nombre recorrer ruta species->name
    var species: Species?=null

    @SerializedName("height")
    var height: String? = null

    @SerializedName("base_experience")
    var baseExperience: String? = null

    @SerializedName("weight")
    var weight: String? = null

    //Imagen pokemon
    @SerializedName("sprites")
    var sprites: Sprites? = null

    @SerializedName("id")
    var id: String? = null

    @SerializedName("order")
    var order: String? = null

}
class Species{
    @SerializedName("name")
    var name: String? = null
}

class Sprites{
    @SerializedName("other")
    var other: Other? = null
}
class Other{
    @SerializedName("official-artwork")
    var officialArtwork: OfficialArtwork? = null
}
class OfficialArtwork{
    @SerializedName("front_default")
    var frontDefault: String? = null
}