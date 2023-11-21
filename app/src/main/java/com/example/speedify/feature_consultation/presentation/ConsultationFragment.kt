package com.example.speedify.feature_consultation.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.example.speedify.R
import com.example.speedify.feature_consultation.presentation.adapter.MontirViewPagerAdapter
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.OnChangedCallback
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ConsultationFragment : Fragment() {

    private lateinit var viewPagerConsul: ViewPager2

    private lateinit var tabLayout: TabLayout

    private lateinit var viewPagerAdapter: MontirViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_consultation, container, false)

        tabLayout = view.findViewById(R.id.tab_layout)
        viewPagerConsul = view.findViewById(R.id.view_pager)

        viewPagerAdapter = MontirViewPagerAdapter(childFragmentManager, lifecycle)

        tabLayout.addTab(tabLayout.newTab().setText("Tersedia"))
        tabLayout.addTab(tabLayout.newTab().setText("Teratas"))
        tabLayout.addTab(tabLayout.newTab().setText("Terpercaya"))

        viewPagerConsul.adapter = viewPagerAdapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPagerConsul.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        viewPagerConsul.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

        return view
    }

    companion object {
        // ...
    }
}
