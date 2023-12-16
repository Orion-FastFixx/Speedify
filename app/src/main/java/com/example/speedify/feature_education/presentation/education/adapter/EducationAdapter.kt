package com.example.speedify.feature_education.presentation.education.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.speedify.databinding.ItemCardEducationBinding
import com.example.speedify.feature_bengkel.presentation.home.adapter.PromotionAdapter
import com.example.speedify.feature_education.domain.entity.EducationEntity
import com.example.speedify.feature_education.presentation.detail_education.DetailEducationActivity
import com.example.speedify.core.utils.BaseAdapter
import com.example.speedify.core.utils.DiffCallbackListener

class EducationAdapter :
    BaseAdapter<EducationEntity, ItemCardEducationBinding>(diffCallbackListener) {

    companion object {
        val diffCallbackListener = object : DiffCallbackListener<EducationEntity> {
            override fun areItemsTheSame(oldItem: EducationEntity, newItem: EducationEntity) =
                oldItem.id == newItem.id
        }
    }

    private lateinit var onItemClickCallback: PromotionAdapter.OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: PromotionAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun createViewHolder(inflater: LayoutInflater, container: ViewGroup) =
        ItemCardEducationBinding.inflate(inflater, container, false)

    override fun bind(
        binding: ItemCardEducationBinding,
        item: EducationEntity,
        position: Int,
        count: Int,
        context: Context
    ) {
        binding.imgCardEducation.setImageResource(item.gambar)
        binding.tvCardTitleEducation.text = item.nama
        binding.tvCardSubtitleEducation.text = item.jenis_kendaraan

        binding.root.setOnClickListener {
            binding.root.context.startActivity(
                Intent(
                    binding.root.context,
                    DetailEducationActivity::class.java
                )
            )
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: EducationEntity)
    }
}