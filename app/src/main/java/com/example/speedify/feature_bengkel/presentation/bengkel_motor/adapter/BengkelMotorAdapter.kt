package com.example.speedify.feature_bengkel.presentation.bengkel_motor.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.speedify.core.utils.BaseAdapter
import com.example.speedify.core.utils.DiffCallbackListener
import com.example.speedify.core.utils.fromJson
import com.example.speedify.core.utils.setImageFromUrl
import com.example.speedify.databinding.ItemCardBengkelTwoBinding
import com.example.speedify.feature_bengkel.data.model.DataItem
import com.example.speedify.feature_bengkel.presentation.detail_bengkel.DetailBengkelActivity
import com.example.speedify.feature_bengkel.presentation.home.adapter.PromotionAdapter

class BengkelMotorAdapter :
    BaseAdapter<DataItem, ItemCardBengkelTwoBinding>(diffCallbackListener) {

    companion object {
        val diffCallbackListener = object : DiffCallbackListener<DataItem> {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem) =
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
        item: DataItem,
        position: Int,
        count: Int,
        context: Context
    ) {
        binding.apply {
            val imageUrls: List<String> = fromJson(item.fotoUrl)

            if (imageUrls.isNotEmpty()) {
                imgCardTwo.setImageFromUrl(context, imageUrls[0])
            }
            tvTypeBengkelCardTwo.text = item.jenisBengkel
            tvTitleCardTwo.text = item.namaBengkel
            tvRatingCardTwo.text = item.rating.firstOrNull()?.averageRating?.toString() ?: "0"
            tvReviewCardTwo.text = String.format(
                "(%s reviews)",
                item.rating.firstOrNull()?.reviewCount?.toString() ?: "0"
            )
            tvDurationCardTwo.text = "15 mins"
            tvAreaCardTwo.text = item.alamat
            tvCityCardTwo.text = item.lokasi
            tvDistanceCardTwo.text = "2.1 Km dari Lokasi Anda"
            tvPriceCardTwo.text = "Jasa Mulai dari Rp30.000"

            root.setOnClickListener {
                val intent = Intent(context, DetailBengkelActivity::class.java)
                intent.putExtra(DetailBengkelActivity.EXTRA_BENGKEL_ID, item.id)
                context.startActivity(intent)
            }
        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DataItem)
    }
}