package com.example.speedify.feature_consultation.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.speedify.feature_consultation.presentation.TeratasFragment
import com.example.speedify.feature_consultation.presentation.TerpercayaFragment
import com.example.speedify.feature_consultation.presentation.TersediaFragment


class MontirViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> TersediaFragment()
            1 -> TeratasFragment()
            2 -> TerpercayaFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}
