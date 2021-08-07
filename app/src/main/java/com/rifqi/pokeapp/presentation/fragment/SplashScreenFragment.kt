package com.rifqi.pokeapp.presentation.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rifqi.pokeapp.R
import com.rifqi.pokeapp.databinding.FragmentSplashScreenBinding
import com.rifqi.pokeapp.utils.BaseFragment

class SplashScreenFragment : BaseFragment(){

    private var _binding : FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun initView(savedInstanceState: Bundle?) {
        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            findNavController().navigate(R.id.action_splashScreenFragment_to_mainFragment)
        }, 3000)
    }

    override fun initObservable() {

    }
}