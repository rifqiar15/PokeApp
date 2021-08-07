package com.rifqi.pokeapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rifqi.pokeapp.domain.PokemonUseCase
import com.rifqi.pokeapp.domain.Resource
import com.rifqi.pokeapp.domain.entity.ItemPokemon
import com.rifqi.pokeapp.domain.successOr
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PokemonViewModel(private val pokemonUseCase: PokemonUseCase) : ViewModel() {

    private val _loadingState = MutableLiveData<Resource<Nothing>>()
    val loadingState: LiveData<Resource<Nothing>>
        get() = _loadingState

    private val _listPokemon = MutableLiveData<List<ItemPokemon>>()
    val listPokemon: LiveData<List<ItemPokemon>>
        get() = _listPokemon

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    init {
        getListPokemon()
    }

    fun getListPokemon() = viewModelScope.launch {
        pokemonUseCase.getListPokemon().collect() { result ->
            when(result) {
                is Resource.Success -> {
                    _listPokemon.value = result.successOr(emptyList())
                }
                is Resource.Error -> {
                    _errorMessage.postValue(result.throwable.toString())
                }
                is Resource.Loading -> {

                }
            }
        }
    }
}