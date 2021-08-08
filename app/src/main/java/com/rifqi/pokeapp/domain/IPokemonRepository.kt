package com.rifqi.pokeapp.domain

import com.rifqi.pokeapp.domain.entity.GetDetailPokemon
import com.rifqi.pokeapp.domain.entity.GetEvolutionPokemon
import com.rifqi.pokeapp.domain.entity.ItemPokemon
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {
    fun getListPokemon(url : String) : Flow<Resource<List<ItemPokemon>>>
    fun getDetailPokemon(url : String) : Flow<Resource<GetDetailPokemon>>
    fun getEvolutionPokemon(url : String) : Flow<Resource<GetEvolutionPokemon>>
}