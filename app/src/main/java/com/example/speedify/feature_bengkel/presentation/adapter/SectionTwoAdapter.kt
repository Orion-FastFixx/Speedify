package com.example.speedify.feature_bengkel.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.speedify.databinding.ItemCardBengkelOneBinding
import com.example.speedify.feature_bengkel.domain.entity.BengkelEntity
import com.example.speedify.utils.BaseAdapter
import com.example.speedify.utils.DiffCallbackListener

class SectionTwoAdapter :
    BaseAdapter<BengkelEntity, ItemCardBengkelOneBinding>(diffCallbackListener) {

    companion object {
        val diffCallbackListener = object : DiffCallbackListener<BengkelEntity> {
            override fun areItemsTheSame(oldItem: BengkelEntity, newItem: BengkelEntity) =
                oldItem.id == newItem.id
        }
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
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: BengkelEntity)
    }
}