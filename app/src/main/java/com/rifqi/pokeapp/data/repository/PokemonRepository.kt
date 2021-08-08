package com.rifqi.pokeapp.data.repository

import com.rifqi.pokeapp.data.ApiResponse
import com.rifqi.pokeapp.data.NetworkBoundResource
import com.rifqi.pokeapp.data.datasource.PokemonDataSource
import com.rifqi.pokeapp.domain.IPokemonRepository
import com.rifqi.pokeapp.domain.Resource
import com.rifqi.pokeapp.domain.entity.GetDetailPokemon
import com.rifqi.pokeapp.domain.entity.GetEvolutionPokemon
import com.rifqi.pokeapp.domain.entity.ItemPokemon
import kotlinx.coroutines.flow.Flow

class PokemonRepository(private val pokemonDataSource: PokemonDataSource) : IPokemonRepository {
    override fun getListPokemon(url : String): Flow<Resource<List<ItemPokemon>>> =
        object : NetworkBoundResource<List<ItemPokemon>, List<ItemPokemon>>(){
            override fun loadFromApi(data: List<ItemPokemon>): List<ItemPokemon> = data
            override suspend fun fetchFromNetwork(): Flow<ApiResponse<List<ItemPokemon>>> = pokemonDataSource.getListPokemon(url)
        }.asFlow()

    override fun getDetailPokemon(url: String): Flow<Resource<GetDetailPokemon>>  =
        object : NetworkBoundResource<GetDetailPokemon, GetDetailPokemon>() {
            override fun loadFromApi(data: GetDetailPokemon): GetDetailPokemon = data

            override suspend fun fetchFromNetwork(): Flow<ApiResponse<GetDetailPokemon>> = pokemonDataSource.geDetailPokemon(url)
        }.asFlow()

    override fun getEvolutionPokemon(url: String): Flow<Resource<GetEvolutionPokemon>> =
        object : NetworkBoundResource<GetEvolutionPokemon, GetEvolutionPokemon>() {
            override fun loadFromApi(data: GetEvolutionPokemon): GetEvolutionPokemon = data

            override suspend fun fetchFromNetwork(): Flow<ApiResponse<GetEvolutionPokemon>> = pokemonDataSource.getEvolutionPokemon(url)
        }.asFlow()
}