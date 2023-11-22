package com.example.speedify.feature_activity.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.lifecycle.Lifecycle
import com.example.speedify.feature_activity.presentation.CancelFragment
import com.example.speedify.feature_activity.presentation.ProsesFragment
import com.example.speedify.feature_activity.presentation.RiwayatFragment
import com.example.speedify.feature_consultation.presentation.TeratasFragment
import com.example.speedify.feature_consultation.presentation.TerpercayaFragment
import com.example.speedify.feature_consultation.presentation.TersediaFragment


class PesananViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle:  Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> ProsesFragment()
            1 -> RiwayatFragment()
            2 -> CancelFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}
