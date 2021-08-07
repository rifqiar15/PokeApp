package com.rifqi.pokeapp.domain

import com.rifqi.pokeapp.domain.entity.ItemPokemon
import kotlinx.coroutines.flow.Flow

interface IPokemonRepository {
    fun getListPokemon() : Flow<Resource<List<ItemPokemon>>>
}