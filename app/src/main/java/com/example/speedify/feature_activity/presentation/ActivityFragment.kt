package com.example.speedify.feature_activity.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.speedify.R
import com.example.speedify.feature_activity.presentation.adapter.PesananViewPagerAdapter
import com.example.speedify.feature_consultation.presentation.adapter.MontirViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityFragment : Fragment() {

    private lateinit var viewPagerPesan: ViewPager2

    private lateinit var tabLayout: TabLayout

    private lateinit var viewPagerAdapter: PesananViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_activity, container, false)

        tabLayout = view.findViewById(R.id.tb_pesanan)
        viewPagerPesan = view.findViewById(R.id.vp_pesanan)

        viewPagerAdapter = PesananViewPagerAdapter(childFragmentManager, lifecycle)

        viewPagerPesan.adapter = viewPagerAdapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewPagerPesan.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        viewPagerPesan.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

        return view
    }

    companion object {
    }
}