package com.rifqi.pokeapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rifqi.pokeapp.R
import com.rifqi.pokeapp.databinding.FragmentMainBinding
import com.rifqi.pokeapp.domain.Resource
import com.rifqi.pokeapp.domain.entity.ItemPokemon
import com.rifqi.pokeapp.presentation.adapter.ListPokemonAdapter
import com.rifqi.pokeapp.presentation.viewmodel.PokemonViewModel
import com.rifqi.pokeapp.utils.BaseFragment
import com.rifqi.pokeapp.utils.Dialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment() {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val pokemonViewModel by viewModel<PokemonViewModel>()
    lateinit var loading : android.app.Dialog

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
        loading = Dialog.loading(requireContext())
    }

    override fun initObservable() {
        pokemonViewModel.listPokemon.observe(viewLifecycleOwner, {
            setListPokemon(it)
            dismissLoading()
        })
        pokemonViewModel.errorMessage.observe(viewLifecycleOwner, {
            toast(it)
        })
        pokemonViewModel.loadingState.observe(this) {
            when(it){
                is Resource.Loading -> {showLoading()}
                is Resource.Success -> {}
                is Resource.Error -> {dismissLoading()}
            }
        }
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

    fun setListPokemon(data : List<ItemPokemon>){
        val listPokemonAdapter = ListPokemonAdapter{
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment(it!!.url)
            findNavController().navigate(action)
        }
        listPokemonAdapter.setData(data)
        with(binding.rvListDoctor){
            setHasFixedSize(true)
            adapter = listPokemonAdapter
        }
    }
}