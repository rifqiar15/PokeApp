package com.rifqi.pokeapp.domain

sealed class Resource<out T> {
    class Success<out T>(val data: T) : Resource<T>()
    class Error(val throwable: String?) : Resource<Nothing>()
    object Loading : Resource<Nothing>()

    override fun toString(): String {
        return when(this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$throwable]"
            Loading -> "Loading"
        }
    }
}

// `true` if [Result] is of type [Success] & holds non-null [Success.data].
val Resource<*>.succeeded get() = this is Resource.Success && data != null

fun <T> Resource<T>.successOr(fallback: T): T {
    return (this as? Resource.Success<T>)?.data ?: fallback
}

val <T> Resource<T>.data: T? get() = (this as? Resource.Success)?.data