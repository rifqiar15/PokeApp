package com.rifqi.pokeapp.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Evolution (
    @field:SerializedName("from") val from: String,
    @field:SerializedName("to") val to: String,
    @field:SerializedName("level") val level: String
):Parcelable
