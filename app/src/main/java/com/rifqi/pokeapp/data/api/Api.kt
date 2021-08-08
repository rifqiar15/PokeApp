package com.rifqi.pokeapp.data.api

import com.rifqi.pokeapp.domain.entity.GetDetailPokemon
import com.rifqi.pokeapp.domain.entity.GetListPokemon
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface Api {

    @GET
    suspend fun getListPokemon(@Url url : String) : GetListPokemon

    @GET
    suspend fun getDetailPokemon(@Url url : String) : GetDetailPokemon

}