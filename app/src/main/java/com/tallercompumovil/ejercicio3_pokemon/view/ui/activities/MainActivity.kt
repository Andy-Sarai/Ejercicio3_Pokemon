package com.tallercompumovil.ejercicio3_pokemon.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tallercompumovil.ejercicio3_pokemon.R
import com.tallercompumovil.ejercicio3_pokemon.databinding.ActivityMainBinding
import com.tallercompumovil.ejercicio3_pokemon.model.Game
import com.tallercompumovil.ejercicio3_pokemon.model.GamesApi
import com.tallercompumovil.ejercicio3_pokemon.model.Pokemon
import com.tallercompumovil.ejercicio3_pokemon.view.adapter.Adaptador
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), Adaptador.OnItemListener {

    private val BASE_URL = "https://pokeapi.co/"
    private val LOGTAG = "LOGS"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL) //.baseUrl(R.string.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val gamesApi: GamesApi = retrofit.create(GamesApi::class.java)

        val call: Call<Game> = gamesApi.getGames("api/v2/pokemon?limit=151")

        call.enqueue(object: Callback<Game>{
            override fun onResponse(call: Call<Game>, response: Response<Game>) {
                //Se ejecuta cuando conexión exitosa
                /*val pokemonTmp: Game
                for(pokemonTmp in response.body()!!.results!!) {
                    Toast.makeText(this@MainActivity, "Nombre: ${pokemonTmp.name}",Toast.LENGTH_SHORT).show()
                    Log.d(LOGTAG, "Nombre: ${pokemonTmp.name}")
                    binding.pbConexion.visibility = View.INVISIBLE
                }*/

                val adaptador = Adaptador(this@MainActivity, response.body()!!, this@MainActivity)
                with(binding) {
                    rvMenu.layoutManager = LinearLayoutManager(this@MainActivity)
                    rvMenu.adapter = adaptador
                }

            }

            override fun onFailure(call: Call<Game>, t: Throwable) {
                //Se ejecuta cuando algo falla
                Log.d(LOGTAG,"Error")
                Toast.makeText(this@MainActivity, resources.getString(R.string.Error),Toast.LENGTH_LONG).show()
                //binding.pbConexion.visibility = View.INVISIBLE
            }

        })

    }

    override fun onItemClick(game: Pokemon) {
        val parametros = Bundle()
        parametros.putString("name", game.name)
        val intent = Intent(this, Detalles::class.java)
        intent.putExtras(parametros)
        startActivity(intent)
    }
}