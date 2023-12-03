package com.example.speedify.feature_bengkel.presentation.bengkel_motor.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.speedify.databinding.ItemCardBengkelTwoBinding
import com.example.speedify.feature_bengkel.domain.entity.BengkelEntity
import com.example.speedify.feature_bengkel.presentation.adapter.PromotionAdapter
import com.example.speedify.feature_bengkel.presentation.detail_bengkel.DetailBengkelActivity
import com.example.speedify.utils.BaseAdapter
import com.example.speedify.utils.DiffCallbackListener

class BengkelMotorAdapter :
    BaseAdapter<BengkelEntity, ItemCardBengkelTwoBinding>(diffCallbackListener) {

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
        ItemCardBengkelTwoBinding.inflate(inflater, container, false)

    override fun bind(
        binding: ItemCardBengkelTwoBinding,
        item: BengkelEntity,
        position: Int,
        count: Int
    ) {
        binding.imgCardTwo.setImageResource(item.foto)
        binding.tvTypeBengkelCardTwo.text = item.jenis_bengkel
        binding.tvTitleCardTwo.text = item.nama
        binding.tvRatingCardTwo.text = item.rating.toString()
        binding.tvReviewCardTwo.text = String.format("(%s reviews)", item.review)
        binding.tvDurationCardTwo.text = item.waktu
        binding.tvAreaCardTwo.text = String.format("%s, ", item.kecamatan)
        binding.tvCityCardTwo.text = item.kota
        binding.tvDistanceCardTwo.text = String.format("%s Km dari Lokasi Anda", item.lokasi)
        binding.tvPriceCardTwo.text = String.format("Jasa Mulai dari Rp. %s", item.harga)

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