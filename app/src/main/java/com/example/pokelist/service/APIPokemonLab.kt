package com.example.pokelist.service

import com.example.pokelist.interfaces.PokemonLabInterface
import com.example.pokelist.model.Pokemon
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request

object APIPokemonLab: PokemonLabInterface {
    private var mClient: OkHttpClient = OkHttpClient()
    private lateinit var mRequest: Request
    private var mGson: Gson = Gson()

    override fun getPokemonList(): List<Pokemon>? {
        return runBlocking { fetchPokemonList(50) }
    }

    private suspend fun fetchPokemonList(pokemonListLength: Int): MutableList<Pokemon>? {
        val listOfPokemon: MutableList<Pokemon>? = mutableListOf()

        withContext(Dispatchers.IO) {
            try {
                for (i in 1..pokemonListLength) {
                    mRequest = Request.Builder()
                            .url("https://pokeapi.co/api/v2/pokemon/$i/")
                            .build()

                    val response = mClient.newCall(mRequest).execute()
                    val result = response.body()?.string()

                    val newPokemon = mGson.fromJson(result, Pokemon::class.java)
                    listOfPokemon?.add(newPokemon)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        /*withContext(Dispatchers.IO) {
            try {
                val response = mClient.newCall(mRequest).execute()
                val result = response.body()?.string()

                val newPokemon = mGson.fromJson(result, Pokemon::class.java)
                listOfPokemon.add(newPokemon)

            }catch (e: Exception){
                e.printStackTrace()
            }
        }*/
        return listOfPokemon
    }

}