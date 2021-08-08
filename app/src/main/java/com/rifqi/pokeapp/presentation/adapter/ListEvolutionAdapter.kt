package com.rifqi.pokeapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rifqi.pokeapp.databinding.ItemEvolutionBinding
import com.rifqi.pokeapp.domain.entity.Evolution

class ListEvolutionAdapter : RecyclerView.Adapter<ListEvolutionAdapter.EvolutionViewHolder>() {

    private var itemEvolution = ArrayList<Evolution>()

    fun setData(evolution: List<Evolution>?) {
        if (evolution == null) return
        this.itemEvolution.clear()
        this.itemEvolution.addAll(evolution)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvolutionViewHolder {
        val itemsBinding = ItemEvolutionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListEvolutionAdapter.EvolutionViewHolder(itemsBinding)
    }

    override fun onBindViewHolder(holder: EvolutionViewHolder, position: Int) {
        val evolution = itemEvolution[position]
        holder.bind(evolution)
    }

    override fun getItemCount(): Int = itemEvolution.size

    class EvolutionViewHolder(private val binding : ItemEvolutionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(evolution: Evolution) {
            with(binding){
                tvEvolutionFrom.text = evolution.from
                tvEvolutionTo.text = evolution.to
                tvEvolutionLevel.text = evolution.level
            }
        }
    }
}