package com.rifqi.pokeapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rifqi.pokeapp.domain.PokemonUseCase
import com.rifqi.pokeapp.domain.Resource
import com.rifqi.pokeapp.domain.entity.GetDetailPokemon
import com.rifqi.pokeapp.domain.entity.ItemPokemon
import com.rifqi.pokeapp.domain.successOr
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
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

    private val _detailPokemon = MutableLiveData<GetDetailPokemon>()
    val detailPokemon: LiveData<GetDetailPokemon>
        get() = _detailPokemon

    init {
        getListPokemon("https://pokeapi.co/api/v2/pokemon/?offset=0&limit=20")
    }

    fun getListPokemon(url : String) = viewModelScope.launch {
        _loadingState.value = Resource.Loading
        pokemonUseCase.getListPokemon(url).collect() { result ->
            when(result) {
                is Resource.Success -> {
                    _listPokemon.value = result.successOr(emptyList())
                }
                is Resource.Error -> {
                    _errorMessage.postValue(result.throwable.toString())
                    _loadingState.value = Resource.Error(result.throwable.toString())
                }
            }
        }
    }

    fun getDetailPokemon(url : String) = viewModelScope.launch {
        _loadingState.value = Resource.Loading
        pokemonUseCase.getDetailPokemon(url).collect() { result ->
            when(result) {
                is Resource.Success -> {
                    _detailPokemon.value = result.data
                }
                is Resource.Error -> {
                    _errorMessage.postValue(result.throwable.toString())
                    _loadingState.value = Resource.Error(result.throwable.toString())
                }
            }
        }
    }


}