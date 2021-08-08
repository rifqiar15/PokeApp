package com.rifqi.pokeapp.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rifqi.pokeapp.R
import com.rifqi.pokeapp.databinding.FragmentDetailStatsBinding
import com.rifqi.pokeapp.domain.Resource
import com.rifqi.pokeapp.domain.entity.Abilities
import com.rifqi.pokeapp.domain.entity.GetDetailPokemon
import com.rifqi.pokeapp.domain.entity.Stats
import com.rifqi.pokeapp.presentation.adapter.ListAbilityAdapter
import com.rifqi.pokeapp.presentation.adapter.ListStatsAdapter
import com.rifqi.pokeapp.presentation.viewmodel.PokemonViewModel
import com.rifqi.pokeapp.utils.BaseFragment
import com.rifqi.pokeapp.utils.Constants
import com.rifqi.pokeapp.utils.Dialog
import okhttp3.internal.notifyAll
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailStatsFragment : BaseFragment() {

    private var _binding : FragmentDetailStatsBinding? = null
    private val binding get() = _binding!!
    private val pokemonViewModel by viewModel<PokemonViewModel>()
    lateinit var loading : android.app.Dialog
    private var url : String? = null

    companion object {
        private val ARG_DATA = "argument"

        fun newInstance(url : String) : DetailStatsFragment {
            val args = Bundle()
            args.putString(ARG_DATA, url)
            val fragment = DetailStatsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailStatsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun initView(savedInstanceState: Bundle?) {
        url = arguments?.getString(ARG_DATA)
        loading = Dialog.loading(requireContext())
        pokemonViewModel.getDetailPokemon(url.toString())
    }

    override fun initObservable() {
        pokemonViewModel.detailPokemon.observe(viewLifecycleOwner, {
            setDetailPokemonUI(it)
            dismissLoading()
        })
        pokemonViewModel.errorMessage.observe(viewLifecycleOwner, {
            toast(it)
        })
        pokemonViewModel.loadingState.observe(this) {
            when(it){
                is Resource.Loading -> {showLoading()}
                is Resource.Success -> {dismissLoading()}
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

    fun setDetailPokemonUI(pokemon : GetDetailPokemon){
        Glide.with(requireContext())
            .load(pokemon.sprites.frontDefault)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(binding.ivDetailStat)

        binding.tvDetailStatName.text = pokemon.name
        binding.tvDetailStatType.text = pokemon.types[0].type.name
        setStat(pokemon.stats)
        setAbility(pokemon.abilities)

        Glide.with(requireContext())
            .load(pokemon.sprites.frontDefault)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(binding.ivDetailStatNormal)

        Glide.with(requireContext())
            .load(pokemon.sprites.frontShiny)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error))
            .into(binding.ivDetailStatShiny)
    }

    fun setStat(stats : List<Stats>){
        val listStatsAdapter = ListStatsAdapter()
        listStatsAdapter.setData(stats)
        with(binding.rvDetailStat){
            setHasFixedSize(true)
            adapter = listStatsAdapter
        }
    }

    fun setAbility(abilities : List<Abilities>){
        val listAbilityAdapter = ListAbilityAdapter()
        listAbilityAdapter.setData(abilities)
        with(binding.rvDetailAbility){
            setHasFixedSize(true)
            adapter = listAbilityAdapter
        }
    }

}