package com.example.speedify.feature_consultation.presentation.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.speedify.feature_consultation.presentation.presentation.ConsultationFragment
import com.example.speedify.feature_consultation.presentation.presentation.TeratasFragment
import com.example.speedify.feature_consultation.presentation.presentation.TerpercayaFragment
import com.example.speedify.feature_consultation.presentation.presentation.TersediaFragment


class MyViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

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
