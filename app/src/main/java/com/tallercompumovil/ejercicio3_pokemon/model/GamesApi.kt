package com.tallercompumovil.ejercicio3_pokemon.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface GamesApi {
    //Detalles de los 151 pokemón
    //https://pokeapi.co/api/v2/pokemon?limit=151

    //Detalles del pokemon 149
    //https://pokeapi.co/api/v2/pokemon/149/

    //ENDPOINTS

    @GET
    fun getGames(//obtiene el listado de los pokemones
        @Url url: String?
    ): Call<Game>

    @GET("api/v2/pokemon/{id}")
    fun getGameDetail(//obtiene los detalles del pokemón
        @Path("id") id:String?
        //@Query("id") id: String? //revisar @Path para solo colocar el número de id
    ): Call<GameDetail>


    //getGameDetail("149")

}