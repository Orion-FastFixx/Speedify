package com.example.speedify.feature_bengkel.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.speedify.databinding.ItemCardBengkelOneBinding
import com.example.speedify.feature_bengkel.domain.entity.BengkelEntity
import com.example.speedify.feature_bengkel.presentation.detail_bengkel.DetailBengkelActivity
import com.example.speedify.feature_bengkel.presentation.home.adapter.PromotionAdapter
import com.example.speedify.core.utils.BaseAdapter
import com.example.speedify.core.utils.DiffCallbackListener

class SectionTwoAdapter :
    BaseAdapter<BengkelEntity, ItemCardBengkelOneBinding>(diffCallbackListener) {

    companion object {
        val diffCallbackListener = object : DiffCallbackListener<BengkelEntity> {
            override fun areItemsTheSame(oldItem: BengkelEntity, newItem: BengkelEntity) =
                oldItem.id == newItem.id
        }
    }

    private lateinit var onItemClickCallback: PromotionAdapter.OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: PromotionAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun createViewHolder(inflater: LayoutInflater, container: ViewGroup) =
        ItemCardBengkelOneBinding.inflate(inflater, container, false)

    override fun bind(
        binding: ItemCardBengkelOneBinding,
        item: BengkelEntity,
        position: Int,
        count: Int
    ) {
        binding.imgCardOne.setImageResource(item.foto)
        binding.tvTitleCardOne.text = item.nama
        binding.tvDistanceCardOne.text = item.lokasi
        binding.tvDurationCardOne.text = item.waktu
        binding.tvRatingCardOne.text = item.rating.toString()
        binding.tvReviewCardOne.text = String.format("(%s reviews)", item.review)

        binding.root.setOnClickListener {
            binding.root.context.startActivity(
                Intent(
                    binding.root.context,
                    DetailBengkelActivity::class.java
                )
            )
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: BengkelEntity)
    }
}