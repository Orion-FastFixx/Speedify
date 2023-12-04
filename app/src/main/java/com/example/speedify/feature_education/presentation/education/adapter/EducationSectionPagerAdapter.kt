package com.example.speedify.feature_education.presentation.education.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.speedify.feature_education.presentation.education.ExteriorFragment
import com.example.speedify.feature_education.presentation.education.InteriorFragment
import com.example.speedify.feature_education.presentation.education.MesinFragment
import com.example.speedify.feature_education.presentation.education.TipsFragment

class EducationSectionPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> TipsFragment()
            1 -> InteriorFragment()
            2 -> ExteriorFragment()
            3 -> MesinFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }

}