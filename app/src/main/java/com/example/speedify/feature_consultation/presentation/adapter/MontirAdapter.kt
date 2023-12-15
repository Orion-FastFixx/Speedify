package com.example.speedify.feature_consultation.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.speedify.databinding.CardMontirBinding
import com.example.speedify.feature_consultation.domain.entity.MontirEntity
import com.example.speedify.core.utils.BaseAdapter
import com.example.speedify.core.utils.DiffCallbackListener

class MontirAdapter :
    BaseAdapter<MontirEntity, CardMontirBinding>(diffCallbackListener) {

    companion object {
        val diffCallbackListener = object : DiffCallbackListener<MontirEntity> {
            override fun areItemsTheSame(oldItem: MontirEntity, newItem: MontirEntity) =
                oldItem.id == newItem.id
        }
    }

    private var onItemClickCallback: MontirAdapter.OnItemClickCallback? = null

    fun setOnItemClickCallback(callback: MontirAdapter.OnItemClickCallback) {
        onItemClickCallback = callback
    }

    override fun createViewHolder(inflater: LayoutInflater, container: ViewGroup) =
        CardMontirBinding.inflate(inflater, container, false)

    override fun bind(
        binding: CardMontirBinding,
        item: MontirEntity,
        position: Int,
        count: Int
    ) {
        binding.imgMontir.setImageResource(item.imgMontir)
        binding.jenisMontir.text = item.jenisMontir
        binding.namaMontir.text = item.namaMontir
        binding.jlhRating.text = item.jlhRating.toString()
        binding.pengalaman.text = item.pengalaman.toString()
        binding.harga.text = item.harga.toString()
        binding.jlhCostumer.text = item.jlhCostumer.toString()

        binding.root.setOnClickListener {
            onItemClickCallback?.onItemClicked(item)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: MontirEntity)
    }

}