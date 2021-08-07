package com.rifqi.pokeapp.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class GetListPokemon (
    @field:SerializedName("count") val count: Int,
    @field:SerializedName("next") val next: String,
    @field:SerializedName("previous") val previous: String?,
    @field:SerializedName("results") val results : List<ItemPokemon>
)

@Parcelize
data class ItemPokemon (
    @field:SerializedName("name") val name: String,
    @field:SerializedName("url") val url : String
) : Parcelable
