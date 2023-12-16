package com.example.speedify.feature_bengkel.presentation.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.example.speedify.core.utils.BaseAdapter
import com.example.speedify.core.utils.DiffCallbackListener
import com.example.speedify.core.utils.setImageFromUrl
import com.example.speedify.databinding.ItemCardBengkelOneBinding
import com.example.speedify.feature_bengkel.data.model.DataItem
import com.example.speedify.feature_bengkel.presentation.detail_bengkel.DetailBengkelActivity
import com.example.speedify.feature_bengkel.presentation.home.adapter.PromotionAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.internal.managers.FragmentComponentManager

class SectionTwoAdapter :
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
        context: Context?
    ) {
        binding.apply {
            if (context != null) {
                val gson = Gson()
                val type = object : TypeToken<List<String>>() {}.type
                val imageUrls: List<String> = gson.fromJson(item.fotoUrl, type)

                if (imageUrls.isNotEmpty()) {
                    imgCardOne.setImageFromUrl(context, imageUrls[0])
                }
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
                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        FragmentComponentManager.findActivity(it.context) as Activity,
                        Pair(imgCardOne, "img_card_one"),
                        Pair(tvTitleCardOne, "title_card_one"),
                        Pair(tvDistanceCardOne, "distance_card_one"),
                        Pair(tvDurationCardOne, "duration_card_one"),
                        Pair(tvRatingCardOne, "rating_card_one"),
                        Pair(tvReviewCardOne, "review_card_one")
                    )

                Intent(context, DetailBengkelActivity::class.java).also { intent ->
                    context?.startActivity(intent, optionsCompat.toBundle())
                }
            }
        }
    }


    interface OnItemClickCallback {
        fun onItemClicked(data: DataItem)
    }
}