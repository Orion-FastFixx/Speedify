package com.example.speedify.feature_activity.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.speedify.R
import com.example.speedify.databinding.FragmentActivityBinding
import com.example.speedify.feature_activity.presentation.adapter.PesananViewPagerAdapter
import com.example.speedify.feature_activity.presentation.view_model.PesananViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ActivityFragment : Fragment() {

    private var _binding: FragmentActivityBinding? = null

    private val binding get() = _binding!!

    private val viewModel: PesananViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewPager()
    }

    private fun setViewPager() {
        val pesananViewPagerAdapter = PesananViewPagerAdapter(childFragmentManager, lifecycle)
        val viewPager: ViewPager2 = binding.vpPesanan
        viewPager.adapter = pesananViewPagerAdapter
        viewPager.isUserInputEnabled = false
        val tabs: TabLayout = binding.tbPesanan

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLE[position])
        }.attach()
    }

    companion object {
        private val TAB_TITLE = intArrayOf(
            R.string.process,
            R.string.done,
            R.string.cancel,
        )
    }
}