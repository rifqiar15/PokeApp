package com.bithealth.pokeapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bithealth.pokeapp.databinding.FragmentMainBinding
import com.bithealth.pokeapp.utils.BaseFragment

class MainFragment : BaseFragment() {

    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding!!

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

    }
}