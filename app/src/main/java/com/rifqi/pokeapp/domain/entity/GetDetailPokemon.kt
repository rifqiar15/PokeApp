package com.rifqi.pokeapp.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetDetailPokemon (
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("name") val name: String,
    @field:SerializedName("height") val height: Int,
    @field:SerializedName("weight") val weight: Int,
    @field:SerializedName("is_default") val isDefault: Boolean,
    @field:SerializedName("order") val order: Int,
    @field:SerializedName("stats") val stats: List<Stats>,
    @field:SerializedName("species") val species: Species,
    @field:SerializedName("abilities") val abilities: List<Abilities>,
    @field:SerializedName("sprites") val sprites: Sprites,
    @field:SerializedName("types") val types: List<Types>,
): Parcelable


@Parcelize
data class Stats (
    @field:SerializedName("base_stat") val baseStat: Int,
    @field:SerializedName("effort") val effort : Int,
    @field:SerializedName("stat") val stat : Stat,
) : Parcelable

@Parcelize
data class Stat (
    @field:SerializedName("name") val name: String,
) : Parcelable

@Parcelize
data class Species (
    @field:SerializedName("name") val name: String,
    @field:SerializedName("url") val ability: String,
) : Parcelable

@Parcelize
data class Abilities (
    @field:SerializedName("ability") val ability: Ability,
) : Parcelable

@Parcelize
data class Ability (
    @field:SerializedName("name") val name: String,
) : Parcelable

@Parcelize
data class Sprites (
    @field:SerializedName("front_default") val frontDefault: String,
    @field:SerializedName("front_shiny") val frontShiny : String
) : Parcelable

@Parcelize
data class Types (
    @field:SerializedName("slot") val slot: String,
    @field:SerializedName("type") val type : Type
) : Parcelable

@Parcelize
data class Type (
    @field:SerializedName("name") val name: String,
    @field:SerializedName("url") val url : String
) : Parcelable