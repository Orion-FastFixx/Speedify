package com.example.speedify.feature_bengkel.presentation.detail_bengkel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.speedify.core.utils.BaseAdapter
import com.example.speedify.core.utils.DiffCallbackListener
import com.example.speedify.core.utils.currencyFormat
import com.example.speedify.core.utils.toCamelCase
import com.example.speedify.databinding.ItemCheckboxBengkelServicesBinding
import com.example.speedify.feature_bengkel.data.model.ServicesItem
import com.example.speedify.feature_bengkel.presentation.home.adapter.PromotionAdapter

class BengkelServicesAdapter :
    BaseAdapter<ServicesItem, ItemCheckboxBengkelServicesBinding>(diffCallbackListener) {

    companion object {
        val diffCallbackListener = object : DiffCallbackListener<ServicesItem> {
            override fun areItemsTheSame(oldItem: ServicesItem, newItem: ServicesItem) =
                oldItem.id == newItem.id
        }
    }

    private val selectedServices = mutableListOf<ServicesItem>()

    private lateinit var onItemClickCallback: PromotionAdapter.OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: PromotionAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun createViewHolder(inflater: LayoutInflater, container: ViewGroup) =
        ItemCheckboxBengkelServicesBinding.inflate(inflater, container, false)

    override fun bind(
        binding: ItemCheckboxBengkelServicesBinding,
        item: ServicesItem,
        position: Int,
        count: Int,
        context: Context
    ) {
        binding.cbItemDetailBengkel.apply {
            text = item.layanan.toCamelCase()
            isChecked = selectedServices.contains(item)

            setOnClickListener {
                if (isChecked) {
                    selectedServices.add(item)
                } else {
                    selectedServices.remove(item)
                }
            }
        }
        val formattedPrice = item.hargaLayanan.harga.currencyFormat()
        binding.tvItemPriceDetailBengkel.text = formattedPrice
    }

    // Method to get the selected services
    fun getSelectedServices(): List<ServicesItem> = selectedServices

    interface OnItemClickCallback {
        fun onItemClicked(data: ServicesItem)
    }
}