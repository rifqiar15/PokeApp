package com.rifqi.pokeapp.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetEvolutionPokemon (
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("baby_trigger_item") val babyTriggerItem: String,
    @field:SerializedName("chain") val chain: Chain,
):Parcelable

@Parcelize
data class Chain (
    @field:SerializedName("evolution_details") val evolutionDetail: List<EvolutionDetail>?,
    @field:SerializedName("evolves_to") val evolvesTo: List<EvolvesTo>?,
    @field:SerializedName("species") val species : EvoSpecies?,
    @field:SerializedName("is_baby") val isBaby : Boolean?
) : Parcelable

@Parcelize
data class EvolutionDetail (
    @field:SerializedName("min_level") val minLevel: String?,
) : Parcelable

@Parcelize
data class EvolvesTo (
    @field:SerializedName("evolution_details") val evolutionDetail: List<EvolutionDetail>?,
    @field:SerializedName("evolves_to") val evolvesTo: List<EvolvesTo>?,
    @field:SerializedName("species") val species : EvoSpecies?,
    @field:SerializedName("is_baby") val isBaby : Boolean?
) : Parcelable

@Parcelize
data class EvoSpecies (
    @field:SerializedName("name") val name: String,
    @field:SerializedName("url") val ability: String,
) : Parcelable
