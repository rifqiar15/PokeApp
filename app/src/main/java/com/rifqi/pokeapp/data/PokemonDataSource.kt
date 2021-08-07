package com.rifqi.pokeapp.data

import com.rifqi.pokeapp.data.api.Api
import com.rifqi.pokeapp.domain.entity.ItemPokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class PokemonDataSource(private val api : Api) {

    suspend fun getListPokemon(): Flow<ApiResponse<List<ItemPokemon>>> {
        return flow {
            try {
                val response = api.getListPokemon()
                val listPokemon = response.results
                if(listPokemon.isNotEmpty()) {
                    emit(ApiResponse.Success(listPokemon))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}