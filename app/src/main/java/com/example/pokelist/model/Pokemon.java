package com.example.pokelist.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Pokemon implements Comparable<Pokemon>{
    @Nullable
    private String name;
    @Nullable
    private int id;
    @Nullable
    @SerializedName(value ="sprites")
    private PokemonSprite sprite;
    @Nullable
    public PokemonSprite getSprite() {
        return sprite;
    }

    public void setSprite(@Nullable PokemonSprite sprite) {
        this.sprite = sprite;
    }

    public String getPokemonTitle(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("N.° ").append(getId()).append(" | ").append(getName().toUpperCase());
        return stringBuilder.toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Pokemon anotherPokemon) {
        if(this.id > anotherPokemon.id){
            return 1;
        } if(this.id == anotherPokemon.id){
            return 0;
        } else{
            return -1;
        }
    }
}
