package com.example.speedify.feature_consultation.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.speedify.R
import com.example.speedify.databinding.FragmentConsultationBinding
import com.example.speedify.feature_consultation.presentation.adapter.MontirViewPagerAdapter
import com.example.speedify.feature_consultation.presentation.view_model.ConsultationViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConsultationFragment : Fragment() {

    private var _binding: FragmentConsultationBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ConsultationViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConsultationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewPager()
    }

    private fun setViewPager() {
        val montirViewPagerAdapter = MontirViewPagerAdapter(childFragmentManager, lifecycle)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = montirViewPagerAdapter
        viewPager.isUserInputEnabled = false
        val tabs: TabLayout = binding.tabLayout

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLE[position])
        }.attach()
    }

    companion object {
        private val TAB_TITLE = intArrayOf(
            R.string.available,
            R.string.top,
            R.string.trusted,
        )
    }
}
