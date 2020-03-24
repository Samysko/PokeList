package com.example.pokelist.repositories.local;

import android.content.Context;

import com.example.pokelist.interfaces.PokemonLabInterface;
import com.example.pokelist.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * Inside this class we are going to fetch the pokemon data and information is going to
 * be available at any part of the project.
 */
public class FakePokemonLab implements PokemonLabInterface {
    private Context mContext;
    private static FakePokemonLab sFakePokemonLab;

    /**
     * Fake fetching of pokemons.
     *
     * @return Pokemon list.
     */
    @Override
    public List<Pokemon> getPokemonList(){

        Pokemon myPokemon = new Pokemon();
        myPokemon.setName("Pikachu");
        myPokemon.setId(1);

        Pokemon myPokemonOne = new Pokemon();
        myPokemonOne.setName("Pokacho");
        myPokemonOne.setId(2);

        Pokemon myPokemonTwo = new Pokemon();
        myPokemonTwo.setName("Pikichi");
        myPokemonTwo.setId(3);

        List<Pokemon> pokemonList = new ArrayList<Pokemon>();
        pokemonList.add(myPokemon);
        pokemonList.add(myPokemonOne);
        pokemonList.add(myPokemonTwo);
        return pokemonList;

    }

    private FakePokemonLab(Context context){
         mContext = context.getApplicationContext();
    }

    public static FakePokemonLab get(Context context){
        if(sFakePokemonLab == null){
            return new FakePokemonLab(context);
        }
        return sFakePokemonLab;
    }

}
