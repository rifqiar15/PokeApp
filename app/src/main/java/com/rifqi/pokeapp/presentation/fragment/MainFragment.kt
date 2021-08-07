package com.rifqi.pokeapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rifqi.pokeapp.databinding.FragmentMainBinding
import com.rifqi.pokeapp.domain.entity.ItemPokemon
import com.rifqi.pokeapp.presentation.adapter.ListPokemonAdapter
import com.rifqi.pokeapp.presentation.viewmodel.PokemonViewModel
import com.rifqi.pokeapp.utils.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment() {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val pokemonViewModel by viewModel<PokemonViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun initObservable() {
        pokemonViewModel.listPokemon.observe(viewLifecycleOwner, {
            setListPokemon(it)
        })
    }

    fun setListPokemon(data : List<ItemPokemon>){
        val listPokemonAdapter = ListPokemonAdapter{
//            movePageToDoctorSchedule()
        }
        listPokemonAdapter.setData(data)
        with(binding.rvListDoctor){
            setHasFixedSize(true)
            adapter = listPokemonAdapter
        }
    }
}