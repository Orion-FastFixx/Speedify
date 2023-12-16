package com.example.speedify.feature_bengkel.presentation.home.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.speedify.databinding.ItemPromotionBinding
import com.example.speedify.feature_bengkel.domain.entity.PromotionEntity
import com.example.speedify.core.utils.BaseAdapter
import com.example.speedify.core.utils.DiffCallbackListener

class PromotionAdapter : BaseAdapter<PromotionEntity, ItemPromotionBinding>(diffCallBackListener) {
    companion object {
        val diffCallBackListener = object : DiffCallbackListener<PromotionEntity> {
            override fun areItemsTheSame(oldItem: PromotionEntity, newItem: PromotionEntity) =
                oldItem.id == newItem.id
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun createViewHolder(inflater: LayoutInflater, container: ViewGroup) =
        ItemPromotionBinding.inflate(inflater, container, false)

    override fun bind(
        binding: ItemPromotionBinding,
        item: PromotionEntity,
        position: Int,
        count: Int,
        context: Context
    ) {
        binding.imgPromotion.setImageResource(item.foto)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: PromotionEntity)
    }
}