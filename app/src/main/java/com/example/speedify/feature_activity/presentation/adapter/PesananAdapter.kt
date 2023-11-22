package com.example.speedify.feature_activity.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.speedify.databinding.CardActivityBinding
import com.example.speedify.feature_activity.domain.entity.PesananEntity
import com.example.speedify.feature_consultation.domain.entity.MontirEntity
import com.example.speedify.utils.BaseAdapter
import com.example.speedify.utils.DiffCallbackListener

class PesananAdapter :
    BaseAdapter<PesananEntity, CardActivityBinding>(diffCallbackListener) {

    companion object {
        val diffCallbackListener = object : DiffCallbackListener<PesananEntity> {
            override fun areItemsTheSame(oldItem: PesananEntity, newItem: PesananEntity) =
                oldItem.id == newItem.id
        }
    }

    override fun createViewHolder(inflater: LayoutInflater, container: ViewGroup) =
        CardActivityBinding.inflate(inflater, container, false)

    override fun bind(
        binding: CardActivityBinding,
        item: PesananEntity,
        position: Int,
        count: Int
    ) {
        binding.imgPesanan.setImageResource(item.imgPesanan)
        binding.tgl.text = item.tgl.toString()
        binding.namaBengkel.text = item.namaBengkel
        binding.kendala.text = item.kendala
        binding.codeConfirm.text = item.codeConfirm.toString()
        binding.harga.text = item.harga.toString()
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: PesananEntity)
    }

}