package com.example.speedify.feature_consultation.presentation.adapter

import android.content.Context
import android.content.Intent
import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.speedify.databinding.CardMontirBinding
import com.example.speedify.feature_consultation.domain.entity.MontirEntity
import com.example.speedify.core.utils.BaseAdapter
import com.example.speedify.core.utils.DiffCallbackListener
import com.example.speedify.core.utils.setImageFromUrl
import com.example.speedify.feature_bengkel.presentation.detail_bengkel.DetailBengkelActivity
import com.example.speedify.feature_consultation.data.model.DaftarItem
import com.example.speedify.feature_consultation.presentation.ConsultationFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Locale

class MontirAdapter :
    BaseAdapter<DaftarItem, CardMontirBinding>(diffCallbackListener) {

    companion object {
        val diffCallbackListener = object : DiffCallbackListener<DaftarItem> {
            override fun areItemsTheSame(oldItem: DaftarItem, newItem: DaftarItem) =
                oldItem.id == newItem.id
        }
    }

    private lateinit var onItemClickCallback: MontirAdapter.OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: MontirAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun createViewHolder(inflater: LayoutInflater, container: ViewGroup) =
        CardMontirBinding.inflate(inflater, container, false)

    override fun bind(
        binding: CardMontirBinding,
        item: DaftarItem,
        position: Int,
        count: Int,
        context: Context
    ) {
        binding.apply {
            val gson = Gson()
            val type = object : TypeToken<List<String>>() {}.type
//            val imageUrls: List<String> = gson.fromJson(item.fotoUrl, type)
//
//            if (imageUrls.isNotEmpty()) {
//                imgMontir.setImageFromUrl(context, imageUrls[0])
//            }
            jenisMontir.text = item.jenisMontir
            namaMontir.text = item.nama
            val hargaLayanan = item.services.firstOrNull()?.hargaLayanan?.harga ?: 0
            val formattedHarga = NumberFormat.getNumberInstance(Locale.getDefault()).format(hargaLayanan)
            hargaMontir.text = formattedHarga
            jlhRating.text = (item.rating.firstOrNull()?.averageRating as? Number)?.toString() ?: "belum ada rate"
            jlhreview.text = String.format("%s", item.rating.firstOrNull()?.reviewCount ?: 0)
            pengalaman.text = String.format("%s tahun", item.pengalaman.toString())
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: DaftarItem)
    }

}