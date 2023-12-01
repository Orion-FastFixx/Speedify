package com.example.speedify.feature_bengkel.presentation.detail_bengkel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.speedify.databinding.ItemCheckboxBengkelServicesBinding
import com.example.speedify.feature_bengkel.domain.entity.LayananEntity
import com.example.speedify.feature_bengkel.presentation.adapter.PromotionAdapter
import com.example.speedify.utils.BaseAdapter
import com.example.speedify.utils.DiffCallbackListener
import com.example.speedify.utils.currencyFormat

class BengkelServicesAdapter :
    BaseAdapter<LayananEntity, ItemCheckboxBengkelServicesBinding>(diffCallbackListener) {

    companion object {
        val diffCallbackListener = object : DiffCallbackListener<LayananEntity> {
            override fun areItemsTheSame(oldItem: LayananEntity, newItem: LayananEntity) =
                oldItem.item_name == newItem.item_name
        }
    }

    private val selectedServices = mutableListOf<LayananEntity>()

    private lateinit var onItemClickCallback: PromotionAdapter.OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: PromotionAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun createViewHolder(inflater: LayoutInflater, container: ViewGroup) =
        ItemCheckboxBengkelServicesBinding.inflate(inflater, container, false)

    override fun bind(
        binding: ItemCheckboxBengkelServicesBinding,
        item: LayananEntity,
        position: Int,
        count: Int
    ) {
        binding.cbItemDetailBengkel.apply {
            text = item.item_name
            isChecked = selectedServices.contains(item)

            setOnClickListener {
                if (isChecked) {
                    selectedServices.add(item)
                } else {
                    selectedServices.remove(item)
                }
            }
        }
        val formattedPrice = item.harga.currencyFormat()
        binding.tvItemPriceDetailBengkel.text = formattedPrice
    }

    // Method to get the selected services
    fun getSelectedServices(): List<LayananEntity> = selectedServices

    interface OnItemClickCallback {
        fun onItemClicked(data: LayananEntity)
    }
}