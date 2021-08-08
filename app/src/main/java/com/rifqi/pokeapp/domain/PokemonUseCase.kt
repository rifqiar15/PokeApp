package com.rifqi.pokeapp.domain

import com.rifqi.pokeapp.domain.entity.ItemPokemon
import kotlinx.coroutines.flow.Flow
import com.rifqi.pokeapp.domain.Resource
import com.rifqi.pokeapp.domain.entity.GetDetailPokemon

interface PokemonUseCase {
    fun getListPokemon(url : String) : Flow<Resource<List<ItemPokemon>>>
    fun getDetailPokemon(url : String) : Flow<Resource<GetDetailPokemon>>
}