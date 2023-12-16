package com.example.speedify.feature_profile.presentation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.speedify.core.data.local.UserDataStoreImpl
import com.example.speedify.databinding.FragmentSettingBinding
import com.example.speedify.feature_profile.presentation.edit_profile.EditProfileActivity
import com.example.speedify.feature_profile.presentation.kendaraan.DataKendaraanActivity
import com.example.speedify.feature_profile.presentation.privacy_policy.PrivacyPolicyActivity
import com.example.speedify.feature_profile.presentation.setting.SettingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var _binding: FragmentSettingBinding
    private val binding get() = _binding

    @Inject
    lateinit var userDataStore: UserDataStoreImpl

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureViews()
    }

    private fun configureViews() {
        lifecycleScope.launch {
            val user = userDataStore.getUser()
            binding.tvUsername.text = user.name

            binding.layout.setOnClickListener {
                val intent = Intent(requireContext(), EditProfileActivity::class.java)
                startActivity(intent)
            }

            binding.icSetting.setOnClickListener {
                val intent = Intent(requireContext(), SettingActivity::class.java)
                startActivity(intent)
            }

            binding.layoutMenu1.setOnClickListener {
                val intent = Intent(requireContext(), ContactActivity::class.java)
                startActivity(intent)
            }

            binding.layoutMenu2.setOnClickListener {
                val intent = Intent(requireContext(), PrivacyPolicyActivity::class.java)
                startActivity(intent)
            }

            binding.layoutMenu3.setOnClickListener {
                val intent = Intent(requireContext(), DataKendaraanActivity::class.java)
                startActivity(intent)
            }
        }
    }
}