package com.rifqi.pokeapp.data.api

import com.rifqi.pokeapp.domain.entity.GetListPokemon
import retrofit2.http.GET

interface Api {
    @GET("pokemon/?offset=0&limit=20")
    suspend fun getListPokemon() : GetListPokemon
}