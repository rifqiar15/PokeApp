package com.rifqi.pokeapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rifqi.pokeapp.R
import com.rifqi.pokeapp.databinding.FragmentDetailEvolutionBinding
import com.rifqi.pokeapp.domain.Resource
import com.rifqi.pokeapp.domain.entity.Evolution
import com.rifqi.pokeapp.domain.entity.GetEvolutionPokemon
import com.rifqi.pokeapp.presentation.adapter.ListEvolutionAdapter
import com.rifqi.pokeapp.presentation.viewmodel.PokemonViewModel
import com.rifqi.pokeapp.utils.BaseFragment
import com.rifqi.pokeapp.utils.Dialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailEvolutionFragment : BaseFragment(){

    private var _binding : FragmentDetailEvolutionBinding? = null
    private val binding get() = _binding!!
    private val pokemonViewModel by viewModel<PokemonViewModel>()
    lateinit var loading : android.app.Dialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailEvolutionBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun initView(savedInstanceState: Bundle?) {
        loading = Dialog.loading(requireContext())
        pokemonViewModel.getEvolutionPokemon("https://pokeapi.co/api/v2/evolution-chain/3/")
    }

    override fun initObservable() {
        pokemonViewModel.evolutionPokemon.observe(viewLifecycleOwner, {
            setEvolution(it)
            dismissLoading()
        })

        pokemonViewModel.errorMessage.observe(viewLifecycleOwner, {
            showError(it)
        })
        pokemonViewModel.loadingState.observe(this) {
            when(it){
                is Resource.Loading -> {showLoading()}
                is Resource.Success -> { dismissLoading()}
                is Resource.Error -> {dismissLoading()}
            }
        }
    }

    fun setEvolution(data : GetEvolutionPokemon){
        var listEvolution : MutableList<Evolution> = mutableListOf()

        listEvolution.add(
            Evolution(
            data.chain.species?.name.toString(),
            data.chain.evolvesTo?.get(0)?.species?.name.toString(),
            data.chain.evolvesTo?.get(0)?.evolutionDetail?.get(0)?.minLevel.toString()
        )
        )

        listEvolution.add(Evolution(
            data.chain.evolvesTo?.get(0)?.species?.name.toString(),
            data.chain.evolvesTo?.get(0)?.evolvesTo?.get(0)?.species?.name.toString(),
            data.chain.evolvesTo?.get(0)?.evolvesTo?.get(0)?.evolutionDetail?.get(0)?.minLevel.toString()
        ))

        val listEvolutionAdapter = ListEvolutionAdapter()
        listEvolutionAdapter.setData(listEvolution)
        with(binding.rvDetailEvolution){
            setHasFixedSize(true)
            adapter = listEvolutionAdapter
        }

    }

    fun showError(error : String){
        Dialog.information(requireContext(), "ERROR", error, R.drawable.error )
    }

    fun showLoading(){
        if (!loading.isShowing){
            loading.show()
        }
    }

    fun dismissLoading(){
        if(loading.isShowing){
            loading.dismiss()
        }
    }
}