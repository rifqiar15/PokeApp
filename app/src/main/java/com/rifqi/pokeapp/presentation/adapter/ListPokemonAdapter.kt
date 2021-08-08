package com.rifqi.pokeapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rifqi.pokeapp.databinding.ItemPokemonBinding
import com.rifqi.pokeapp.domain.entity.ItemPokemon

class ListPokemonAdapter(private val onSelect: (ItemPokemon?) -> Unit) : RecyclerView.Adapter<ListPokemonAdapter.PokemonViewHolder>() {

    private var itemPokemon = ArrayList<ItemPokemon>()

    fun setData(pokemon: List<ItemPokemon>?) {
        if (pokemon == null) return
        this.itemPokemon.clear()
        this.itemPokemon.addAll(pokemon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemsBinding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListPokemonAdapter.PokemonViewHolder(itemsBinding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = itemPokemon[position]
        holder.bind(pokemon, onSelect)
    }

    override fun getItemCount(): Int = itemPokemon.size

    class PokemonViewHolder(private val binding : ItemPokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemPokemon: ItemPokemon, onSelect: (ItemPokemon?) -> Unit) {
            with(binding){
                tvItemPokemonName.text = itemPokemon.name
                root.setOnClickListener {
                    onSelect(itemPokemon)
                }
            }
        }
    }
}