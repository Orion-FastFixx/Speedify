package com.example.speedify.feature_education.presentation.detail_education

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.speedify.core.data.local.UserDataStoreImpl
import com.example.speedify.core.utils.fromJson
import com.example.speedify.core.utils.setImageFromUrl
import com.example.speedify.databinding.ActivityDetailEducationBinding
import com.example.speedify.feature_education.presentation.detail_education.view_model.DetailEducationViewModel
import com.example.speedify.feature_education.presentation.education.adapter.EducationAdapter
import com.example.speedify.feature_education.presentation.education.view_model.EducationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailEducationActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityDetailEducationBinding

    private val binding get() = _binding


    private val viewModel: DetailEducationViewModel by viewModels()

    private val educationAdapter by lazy {
        EducationAdapter()
    }

    @Inject
    lateinit var userDataStore: UserDataStoreImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailEducationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val educationId = intent.getIntExtra(EXTRA_EDUCATION_ID, 0)

        if (educationId != 0) {
            viewModel.getDetailEducation(educationId)
        } else {
            Log.e(ContentValues.TAG, "Education ID is not provided")
        }

        observeDetailEducation()

        val iconBack = binding.icBack
        iconBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun observeDetailEducation() {
        lifecycleScope.launch {
            try {
                viewModel.detailEducationState.collect { state ->
                    if (state.isLoading) {
                        Log.d(ContentValues.TAG, "DetailEducation:   Loading")
                    } else if (state.error != null) {
                        Log.e(ContentValues.TAG, "DetailEducation:   ${state.error}")
                    } else {

                        binding.apply {
                            tvTitleToolbar.text = state.detailEducation?.judul

                            // image
//                            val imageUrls = state.detailEducation?.fotoUrl ?: "[]"
//                            val image: List<String> = fromJson(imageUrls)
//                            if (image.isNotEmpty()) {
//                                ivDetailEducation.setImageFromUrl(
//                                    this@DetailEducationActivity,
//                                    image[0]
//                                )
//                            }
                            //     end image

                            tvTitleDetailEducation.text = state.detailEducation?.judul
                            tvSubtitleDetailEducation.text = state.detailEducation?.subJudul
                            tvContentDetailEducation.text = state.detailEducation?.isiKonten

                        }
                    }

                }

            } catch (e: Exception) {
                Log.e(ContentValues.TAG, "observeDetailEducation: ${e.message}")
            }
        }
    }

    companion object {
        const val EXTRA_EDUCATION_ID = "EDUCATION_ID"
    }
}