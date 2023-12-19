package com.example.speedify.feature_activity.presentation.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.speedify.databinding.ItemActivityBinding
import com.example.speedify.core.utils.BaseAdapter
import com.example.speedify.core.utils.DiffCallbackListener
import com.example.speedify.core.utils.toFormattedDate
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
//            val gson = Gson()
//            val type = object : TypeToken<List<String>>() {}.type
//            val imageUrls: List<String> = gson.fromJson(item.fotoUrl, type)
//
//            if (imageUrls.isNotEmpty()) {
//                imgPesanan.setImageFromUrl(context, imageUrls[0])
//            }

            nama.text = item.bengkel?.namaBengkel ?: item.montir?.nama
            tujuan.text = item.additionalInfo?.preciseLocation
            tgl.text = item.createdAt.toFormattedDate()
            harga.text = String.format("Rp. ", item.services?.firstOrNull()?.orderServices?.price)



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