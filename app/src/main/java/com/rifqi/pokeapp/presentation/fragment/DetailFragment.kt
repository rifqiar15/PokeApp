package com.rifqi.pokeapp.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.rifqi.pokeapp.databinding.FragmentDetailBinding
import com.rifqi.pokeapp.presentation.adapter.DetailPagerAdapter
import com.rifqi.pokeapp.presentation.viewmodel.PokemonViewModel
import com.rifqi.pokeapp.utils.BaseFragment
import com.rifqi.pokeapp.utils.Constants
import com.rifqi.pokeapp.utils.`interface`.OnDataReceivedListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment: BaseFragment(){

    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args : DetailFragmentArgs by navArgs<DetailFragmentArgs>()
    private val pokemonViewModel by viewModel<PokemonViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun initView(savedInstanceState: Bundle?) {
        setViewPager(args.url.toString())
        pokemonViewModel.getDetailPokemon(args.url.toString())
    }

    override fun initObservable() {

    }

    fun setViewPager(url : String){
        val pokemonDetailsArray = arrayOf(
            "Stats",
            "Evolution",
        )

        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        val adapter = DetailPagerAdapter(parentFragmentManager, lifecycle, url)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = pokemonDetailsArray[position]
        }.attach()

    }
}