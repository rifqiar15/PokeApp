package com.rifqi.pokeapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rifqi.pokeapp.databinding.ItemAbilityBinding
import com.rifqi.pokeapp.databinding.ItemPokemonBinding
import com.rifqi.pokeapp.databinding.ItemStatsBinding
import com.rifqi.pokeapp.domain.entity.Abilities
import com.rifqi.pokeapp.domain.entity.ItemPokemon
import com.rifqi.pokeapp.domain.entity.Stats

class ListAbilityAdapter : RecyclerView.Adapter<ListAbilityAdapter.AbilityViewHolder>() {

    private var itemAbilitiy = ArrayList<Abilities>()

    fun setData(abilities: List<Abilities>?) {
        if (abilities == null) return
        this.itemAbilitiy.clear()
        this.itemAbilitiy.addAll(abilities)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityViewHolder {
        val itemsBinding = ItemAbilityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListAbilityAdapter.AbilityViewHolder(itemsBinding)
    }

    override fun onBindViewHolder(holder: AbilityViewHolder, position: Int) {
        val ability = itemAbilitiy[position]
        holder.bind(ability)
    }

    override fun getItemCount(): Int = itemAbilitiy.size

    class AbilityViewHolder(private val binding : ItemAbilityBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ability: Abilities) {
            with(binding){
                tvItemAbility.text = ability.ability.name
            }
        }
    }
}