package com.rifqi.pokeapp.data.repository

import com.rifqi.pokeapp.data.ApiResponse
import com.rifqi.pokeapp.data.NetworkBoundResource
import com.rifqi.pokeapp.data.PokemonDataSource
import com.rifqi.pokeapp.domain.IPokemonRepository
import com.rifqi.pokeapp.domain.Resource
import com.rifqi.pokeapp.domain.entity.ItemPokemon
import kotlinx.coroutines.flow.Flow

class PokemonRepository(private val pokemonDataSource: PokemonDataSource) : IPokemonRepository {
    override fun getListPokemon(): Flow<Resource<List<ItemPokemon>>> =
        object : NetworkBoundResource<List<ItemPokemon>, List<ItemPokemon>>(){
            override fun loadFromApi(data: List<ItemPokemon>): List<ItemPokemon> = data
            override suspend fun fetchFromNetwork(): Flow<ApiResponse<List<ItemPokemon>>> = pokemonDataSource.getListPokemon()
        }.asFlow()
}