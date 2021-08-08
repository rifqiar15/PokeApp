package com.rifqi.pokeapp.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rifqi.pokeapp.presentation.fragment.DetailEvolutionFragment
import com.rifqi.pokeapp.presentation.fragment.DetailStatsFragment

class DetailPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val urlStats : String) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        when(position) {
            0 -> return DetailStatsFragment.newInstance(urlStats)
        }
        return DetailEvolutionFragment()
    }
}