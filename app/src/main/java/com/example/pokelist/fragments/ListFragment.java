package com.example.pokelist.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokelist.service.APIPokemonLab;
import com.example.pokelist.R;
import com.example.pokelist.model.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mPokemonAdapter;
    private TextView mPokemonTitle;
    private ImageView mPokemonSprite;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mRecyclerView = view.findViewById(R.id.pokemon_recycler_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;

    }

    public void updateUI(){
        List<Pokemon> pokemons = APIPokemonLab.INSTANCE.getPokemonList();

        if(mPokemonAdapter == null){
            mPokemonAdapter = new PokemonAdapter(pokemons);
            mRecyclerView.setAdapter(mPokemonAdapter);
        }else{
            mPokemonAdapter.notifyDataSetChanged();
            mPokemonAdapter = new PokemonAdapter(pokemons);
            mRecyclerView.setAdapter(mPokemonAdapter);
        }
    }

    private class PokemonHolder extends RecyclerView.ViewHolder{
        private Pokemon mPokemon;

        public PokemonHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_pokemon, parent, false));

            mPokemonTitle = itemView.findViewById(R.id.pokemon_title);
            mPokemonSprite = itemView.findViewById(R.id.pokemon_image);
        }

        public void bind(Pokemon pokemon){
            mPokemon = pokemon;
            mPokemonTitle.setText(mPokemon.getPokemonTitle());
            Picasso.get().load(pokemon.getSprite().getFront_default()).into(mPokemonSprite);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    private class PokemonAdapter extends RecyclerView.Adapter<PokemonHolder>{
        private List<Pokemon> mPokemons;

        public PokemonAdapter(List<Pokemon> pokemons){
            mPokemons = pokemons;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @NonNull
        @Override
        public PokemonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new PokemonHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PokemonHolder holder, int position) {
            Pokemon pokemon = mPokemons.get(position);
            holder.bind(pokemon);
        }

        @Override
        public int getItemCount() {
            return mPokemons.size();
        }
    }
}
