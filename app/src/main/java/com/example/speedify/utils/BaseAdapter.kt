package com.example.speedify.utils

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T, V : ViewBinding>(
    private val diffCallbackListener:
    DiffCallbackListener<T>
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {
    private var _items = mutableListOf<T>()

    // Get the LayoutManager from the RecyclerView
    var layoutManager: LinearLayoutManager? = null

    fun setItems(items: List<T>) {
        val diffResult =
            DiffUtil.calculateDiff(
                DiffUtilCallback(
                    _items,
                    items.toMutableList(),
                    diffCallbackListener
                )
            )
        _items = items.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }

    fun getItems() = _items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseAdapter.BaseViewHolder {
        return BaseViewHolder(createViewHolder(LayoutInflater.from(parent.context), parent))
    }

    override fun onBindViewHolder(holder: BaseAdapter.BaseViewHolder, position: Int) {
        bind(holder.binding as V, _items[position], position, _items.size)


        // Check if it's a horizontal LinearLayoutManager and if it's the last item
        if (layoutManager?.orientation == LinearLayoutManager.HORIZONTAL && position == _items.size - 1) {
            val layoutParams = holder.itemView.layoutParams as RecyclerView.LayoutParams
            layoutParams.marginEnd = 16.dpToPx(holder.itemView.context) // Convert dp to pixels
            holder.itemView.layoutParams = layoutParams
        }
    }

    override fun getItemCount() = _items.size

    abstract fun createViewHolder(inflater: LayoutInflater, container: ViewGroup): ViewBinding

    abstract fun bind(binding: V, item: T, position: Int, count: Int)

    class BaseViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

    fun Int.dpToPx(context: Context): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, this.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }
}