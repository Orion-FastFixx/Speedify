package com.example.speedify.feature_activity.presentation.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.speedify.core.utils.BaseAdapter
import com.example.speedify.core.utils.DiffCallbackListener
import com.example.speedify.core.utils.currencyFormatWithoutRp
import com.example.speedify.core.utils.toFormattedDate
import com.example.speedify.databinding.ItemActivityBinding
import com.example.speedify.feature_activity.data.model.OrderItem
import com.example.speedify.feature_activity.presentation.detail_pesanan.DetailPesananActivity


class PesananAdapter :
    BaseAdapter<OrderItem, ItemActivityBinding>(diffCallbackListener) {

    companion object {
        val diffCallbackListener = object : DiffCallbackListener<OrderItem> {
            override fun areItemsTheSame(oldItem: OrderItem, newItem: OrderItem) =
                oldItem.id == newItem.id
        }
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(callback: OnItemClickCallback) {
        onItemClickCallback = callback
    }

    override fun createViewHolder(inflater: LayoutInflater, container: ViewGroup) =
        ItemActivityBinding.inflate(inflater, container, false)

    override fun bind(
        binding: ItemActivityBinding,
        item: OrderItem,
        position: Int,
        count: Int,
        context: Context
    ) {
        binding.apply {

            nama.text = item.bengkel.namaBengkel
            tujuan.text = item.additionalInfo.preciseLocation
            tgl.text = item.createdAt.toFormattedDate()

            val priceString = item.services.firstOrNull()?.orderServices?.price
            val intPrice = priceString?.toBigDecimalOrNull()?.toInt()
            val formatterdPrice = intPrice?.currencyFormatWithoutRp()
            harga.text = String.format("Rp%s", formatterdPrice)




            root.setOnClickListener {
                val intent = Intent(context, DetailPesananActivity::class.java)
                intent.putExtra(DetailPesananActivity.EXTRA_PESANAN_ID, item.id)
                context.startActivity(intent)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: OrderItem)
    }
}