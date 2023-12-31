package com.example.speedify.feature_education.presentation.education.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.speedify.databinding.ItemCardEducationBinding
import com.example.speedify.feature_education.presentation.detail_education.DetailEducationActivity
import com.example.speedify.core.utils.BaseAdapter
import com.example.speedify.core.utils.DiffCallbackListener
import com.example.speedify.core.utils.setImageFromUrl
import com.example.speedify.feature_education.data.model.ContentItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EducationAdapter :
    BaseAdapter<ContentItem, ItemCardEducationBinding>(diffCallbackListener) {

    companion object {
        val diffCallbackListener = object : DiffCallbackListener<ContentItem> {
            override fun areItemsTheSame(oldItem: ContentItem, newItem: ContentItem) =
                oldItem.id == newItem.id
        }
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(callback: OnItemClickCallback) {
        onItemClickCallback = callback
    }
    override fun createViewHolder(inflater: LayoutInflater, container: ViewGroup) =
        ItemCardEducationBinding.inflate(inflater, container, false)

    override fun bind(
        binding: ItemCardEducationBinding,
        item: ContentItem,
        position: Int,
        count: Int,
        context: Context
    ) {
        binding.apply {
            val gson = Gson()
            val type = object : TypeToken<List<String>>() {}.type
            val imageUrls: List<String> = gson.fromJson(item.fotoUrl, type)

            if (imageUrls.isNotEmpty()) {
                imgEducation.setImageFromUrl(context, imageUrls[0])
            }
            tvCardTitleEducation.text = item.judul
            tvCardSubtitleEducation.text = item.jenisKendaraan

            root.setOnClickListener {
                val intent = Intent(context, DetailEducationActivity::class.java)
                intent.putExtra(DetailEducationActivity.EXTRA_EDUCATION_ID, item.id)
                context.startActivity(intent)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ContentItem)
    }
}