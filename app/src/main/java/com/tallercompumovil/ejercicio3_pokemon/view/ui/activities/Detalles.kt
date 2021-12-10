package com.tallercompumovil.ejercicio3_pokemon.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.tallercompumovil.ejercicio3_pokemon.R
import com.tallercompumovil.ejercicio3_pokemon.databinding.ActivityDetallesBinding
import com.tallercompumovil.ejercicio3_pokemon.model.GameDetail
import com.tallercompumovil.ejercicio3_pokemon.model.GamesApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Detalles : AppCompatActivity() {
    private lateinit var binding: ActivityDetallesBinding
    //private lateinit var binding: ActivityDetallesBinding

    private val BASE_URL = "https://pokeapi.co/"
    private val LOGTAG = "LOGS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesBinding.inflate(layoutInflater)
        //binding= ActivityDetallesBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val bundle = intent.extras
        val name = bundle?.getString("name","0")
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val gamesApi:GamesApi = retrofit.create(GamesApi::class.java)
        val call: Call<GameDetail> = gamesApi.getGameDetail(name)
        call.enqueue(object: Callback<GameDetail>{
            override fun onResponse(call: Call<GameDetail>, response: Response<GameDetail>) {
                with(binding){
                    //pbConexion.visibility = View.INVISIBLE
                    //tvtitle.text = response.body()?.baseExperience
                    /*Glide.with(this@Detalles)
                        .load(response.body?.)
                        .into(ivImage)*/
                    tvWeight.text = response.body()?.weight
                    tvHeight.text = response.body()?.height
                    tvExperience.text = response.body()?.baseExperience
                    tvtitle.text = response.body()?.species?.name
                    tvOrden.text = response.body()?.order
                    tvId.text = response.body()?.id
                    Glide.with(this@Detalles)
                        .load(response.body()?.sprites?.other?.officialArtwork?.frontDefault)
                        .into(binding.tvImage)
                }
            }

            override fun onFailure(call: Call<GameDetail>, t: Throwable) {
                with(binding){
                    //pbConexion.visibility = View.INVISIBLE
                }
                Toast.makeText(this@Detalles, resources.getString(R.string.Error), Toast.LENGTH_LONG).show()
            }

        })

    }
}