package com.rifqi.pokeapp.domain

import com.rifqi.pokeapp.domain.entity.ItemPokemon
import kotlinx.coroutines.flow.Flow

class PokemonInteractor(private val iPokemonRepository: IPokemonRepository) : PokemonUseCase {
    override fun getListPokemon(): Flow<Resource<List<ItemPokemon>>> = iPokemonRepository.getListPokemon()
}