package com.bithealth.pokeapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bithealth.pokeapp.databinding.FragmentDetailBinding
import com.bithealth.pokeapp.databinding.FragmentMainBinding
import com.bithealth.pokeapp.utils.BaseFragment

class DetailFragment: BaseFragment() {

    private var _binding : FragmentDetailBinding? = null
    private val binding get() = _binding!!

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

    }

    override fun initObservable() {

    }
}