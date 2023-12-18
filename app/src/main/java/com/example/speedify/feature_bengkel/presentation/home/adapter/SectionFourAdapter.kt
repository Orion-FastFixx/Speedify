package com.example.speedify.feature_bengkel.presentation.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.speedify.core.utils.BaseAdapter
import com.example.speedify.core.utils.DiffCallbackListener
import com.example.speedify.core.utils.fromJson
import com.example.speedify.core.utils.setImageFromUrl
import com.example.speedify.databinding.ItemCardBengkelOneBinding
import com.example.speedify.feature_bengkel.data.model.DataItem
import com.example.speedify.feature_bengkel.presentation.detail_bengkel.DetailBengkelActivity

class SectionFourAdapter :
    BaseAdapter<DataItem, ItemCardBengkelOneBinding>(diffCallbackListener) {

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
        ItemCardBengkelOneBinding.inflate(inflater, container, false)

    override fun bind(
        binding: ItemCardBengkelOneBinding,
        item: DataItem,
        position: Int,
        count: Int,
        context: Context
    ) {
        binding.apply {
            val imageUrls: List<String> = fromJson(item.fotoUrl)

            if (imageUrls.isNotEmpty()) {
                imgCardOne.setImageFromUrl(context, imageUrls[0])
            }
            tvTitleCardOne.text = item.namaBengkel
            tvDistanceCardOne.text = "2.1 km"
            tvDurationCardOne.text = "15 mins"
            tvRatingCardOne.text = item.rating.firstOrNull()?.averageRating?.toString() ?: "0"
            tvReviewCardOne.text = String.format(
                "(%s reviews)",
                item.rating.firstOrNull()?.reviewCount?.toString() ?: "0"
            )

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