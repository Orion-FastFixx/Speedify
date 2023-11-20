package com.example.speedify.feature_consultation.presentation.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.speedify.R
import com.example.speedify.feature_consultation.presentation.presentation.adapter.MyViewPagerAdapter

class ConsultationFragment : Fragment() {

    private lateinit var viewPagerConsul: ViewPager2
    private lateinit var viewPagerAdapter: MyViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_consultation, container, false)

        viewPagerConsul = view.findViewById(R.id.view_pager)

        // Menggunakan childFragmentManager
        viewPagerAdapter = MyViewPagerAdapter(this)
        viewPagerConsul.adapter = viewPagerAdapter

        return view
    }

    companion object {
        // ...
    }
}
