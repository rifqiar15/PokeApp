package com.rifqi.pokeapp.domain

import com.rifqi.pokeapp.domain.entity.GetDetailPokemon
import com.rifqi.pokeapp.domain.entity.GetEvolutionPokemon
import com.rifqi.pokeapp.domain.entity.ItemPokemon
import kotlinx.coroutines.flow.Flow

class PokemonInteractor(private val iPokemonRepository: IPokemonRepository) : PokemonUseCase {
    override fun getListPokemon(url: String): Flow<Resource<List<ItemPokemon>>> =
        iPokemonRepository.getListPokemon(url)

    override fun getDetailPokemon(url: String): Flow<Resource<GetDetailPokemon>> =
        iPokemonRepository.getDetailPokemon(url)

    override fun getEvolutionPokemon(url: String): Flow<Resource<GetEvolutionPokemon>> =
        iPokemonRepository.getEvolutionPokemon(url)
}