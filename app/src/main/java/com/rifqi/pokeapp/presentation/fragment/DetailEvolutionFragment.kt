package com.rifqi.pokeapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rifqi.pokeapp.databinding.FragmentDetailEvolutionBinding
import com.rifqi.pokeapp.databinding.FragmentDetailStatsBinding
import com.rifqi.pokeapp.presentation.viewmodel.PokemonViewModel
import com.rifqi.pokeapp.utils.BaseFragment
import com.rifqi.pokeapp.utils.`interface`.OnDataReceivedListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailEvolutionFragment : BaseFragment(){

    private var _binding : FragmentDetailEvolutionBinding? = null
    private val binding get() = _binding!!
    private val pokemonViewModel by viewModel<PokemonViewModel>()

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

    }

    override fun initObservable() {
        pokemonViewModel.detailPokemon.observe(viewLifecycleOwner, {
            binding.textTest.text = it.name
        })
    }

}