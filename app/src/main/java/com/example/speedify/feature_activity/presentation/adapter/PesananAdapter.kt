package com.example.speedify.feature_activity.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.speedify.databinding.ItemActivityBinding
import com.example.speedify.feature_activity.domain.entity.PesananEntity
import com.example.speedify.feature_consultation.domain.entity.MontirEntity
import com.example.speedify.utils.BaseAdapter
import com.example.speedify.utils.DiffCallbackListener

class PesananAdapter :
    BaseAdapter<PesananEntity, ItemActivityBinding>(diffCallbackListener) {

    companion object {
        val diffCallbackListener = object : DiffCallbackListener<PesananEntity> {
            override fun areItemsTheSame(oldItem: PesananEntity, newItem: PesananEntity) =
                oldItem.id == newItem.id
        }
    }

    override fun createViewHolder(inflater: LayoutInflater, container: ViewGroup) =
        ItemActivityBinding.inflate(inflater, container, false)

    override fun bind(
        binding: ItemActivityBinding,
        item: PesananEntity,
        position: Int,
        count: Int
    ) {
        binding.imgPesanan.setImageResource(item.imgPesanan)
        binding.namaBengkel.text = item.namaBengkel
        binding.tujuan.text = item.tujuan
        binding.tgl.text = String.format("%s, %s", item.tgl.toLocalDate(), item.tgl.toLocalTime());
        binding.harga.text = String.format("Rp. %s", item.harga)
        binding.ratingBar.rating = item.rating.toFloat()
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: PesananEntity)
    }

}