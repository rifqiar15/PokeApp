package com.rifqi.pokeapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rifqi.pokeapp.databinding.ItemPokemonBinding
import com.rifqi.pokeapp.databinding.ItemStatsBinding
import com.rifqi.pokeapp.domain.entity.ItemPokemon
import com.rifqi.pokeapp.domain.entity.Stats

class ListStatsAdapter : RecyclerView.Adapter<ListStatsAdapter.StatsViewHolder>() {

    private var itemStats = ArrayList<Stats>()

    fun setData(stats: List<Stats>?) {
        if (stats == null) return
        this.itemStats.clear()
        this.itemStats.addAll(stats)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val itemsBinding = ItemStatsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListStatsAdapter.StatsViewHolder(itemsBinding)
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        val stat = itemStats[position]
        holder.bind(stat)
    }

    override fun getItemCount(): Int = itemStats.size

    class StatsViewHolder(private val binding : ItemStatsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemStats: Stats) {
            with(binding){
                tvItemDetailStat.text = itemStats.stat.name
                progressBarItemDetailStat.progress = itemStats.baseStat
            }
        }
    }
}