package com.rifqi.pokeapp.domain

import com.rifqi.pokeapp.domain.entity.ItemPokemon
import kotlinx.coroutines.flow.Flow
import com.rifqi.pokeapp.domain.Resource

interface PokemonUseCase {
    fun getListPokemon() : Flow<Resource<List<ItemPokemon>>>
}