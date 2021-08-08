package com.rifqi.pokeapp.domain

import com.rifqi.pokeapp.domain.entity.ItemPokemon
import kotlinx.coroutines.flow.Flow
import com.rifqi.pokeapp.domain.Resource
import com.rifqi.pokeapp.domain.entity.GetDetailPokemon
import com.rifqi.pokeapp.domain.entity.GetEvolutionPokemon

interface PokemonUseCase {
    fun getListPokemon(url : String) : Flow<Resource<List<ItemPokemon>>>
    fun getDetailPokemon(url : String) : Flow<Resource<GetDetailPokemon>>
    fun getEvolutionPokemon(url : String) : Flow<Resource<GetEvolutionPokemon>>
}