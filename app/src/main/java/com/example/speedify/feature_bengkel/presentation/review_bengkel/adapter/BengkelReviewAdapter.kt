package com.example.speedify.feature_bengkel.presentation.review_bengkel.adapter


import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewbinding.ViewBinding
import com.example.speedify.R
import com.example.speedify.core.utils.BaseAdapter
import com.example.speedify.core.utils.DiffCallbackListener
import com.example.speedify.core.utils.fromJson
import com.example.speedify.core.utils.setImageFromUrl
import com.example.speedify.core.utils.toFormattedDate
import com.example.speedify.databinding.ItemRatingReviewComponentBinding
import com.example.speedify.feature_bengkel.data.model.ReviewAllUser
import com.example.speedify.feature_bengkel.presentation.home.adapter.PromotionAdapter

class BengkelReviewAdapter :
    BaseAdapter<ReviewAllUser, ItemRatingReviewComponentBinding>(diffCallbackListener) {

    companion object {
        val diffCallbackListener = object : DiffCallbackListener<ReviewAllUser> {
            override fun areItemsTheSame(oldItem: ReviewAllUser, newItem: ReviewAllUser) =
                oldItem.id == newItem.id
        }
    }

    private lateinit var onItemClickCallback: PromotionAdapter.OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: PromotionAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun createViewHolder(inflater: LayoutInflater, container: ViewGroup) =
        ItemRatingReviewComponentBinding.inflate(inflater, container, false)

    override fun bind(
        binding: ItemRatingReviewComponentBinding,
        item: ReviewAllUser,
        position: Int,
        count: Int,
        context: Context
    ) {
        binding.apply {
            // val imageUrl: String = fromJson(item.pengendara.foto)
            //
            // if (imageUrl.isNotEmpty()) {
            //     civAvatar.setImageFromUrl(context, imageUrl)
            // }

            tvUserName.text = item.pengendara.nama
            tvDate.text = item.createdAt.toFormattedDate()
            tvReview.text = item.review
            setRatingStars(item.bengkelRating, llContainerStar, context)
        }
    }

    private fun setRatingStars(rating: Number, container: LinearLayout, context: Context) {
        val star1 = container.findViewById<ImageView>(R.id.ic_star_1)
        val halfStar = container.findViewById<ImageView>(R.id.ic_half_star)

        val starTotal = 5;

        val starPercentage = (rating.toFloat() / starTotal.toFloat()) * 100
        val starPercentageRounded = starPercentage.toInt()
        Log.d(ContentValues.TAG, "setRatingStars:   $starPercentageRounded")

        star1.visibility = View.INVISIBLE
        halfStar.visibility = View.INVISIBLE

        val parentLayout = container.findViewById<LinearLayout>(R.id.ll_container_star)
        parentLayout.removeAllViews()

        when (starPercentageRounded) {
            in 0 until 10 -> {
                // Show 0 star
            }

            in 10 until 20 -> {
                addStars(parentLayout, 0, 1, context)
            }

            in 20 until 30 -> {
                addStars(parentLayout, 1, 0, context)
            }

            in 30 until 40 -> {
                addStars(parentLayout, 1, 1, context)
            }

            in 40 until 50 -> {
                addStars(parentLayout, 2, 0, context)
            }

            in 50 until 60 -> {
                addStars(parentLayout, 2, 1, context)
            }

            in 60 until 70 -> {
                addStars(parentLayout, 3, 0, context)
            }

            in 70 until 80 -> {
                addStars(parentLayout, 3, 1, context)
            }

            in 80 until 90 -> {
                addStars(parentLayout, 4, 0, context)
            }

            in 90 until 100 -> {
                addStars(parentLayout, 4, 1, context)
            }

            100 -> {
                addStars(parentLayout, 5, 0, context)
            }
        }
    }
}

private fun addStars(parentLayout: LinearLayout, fullStars: Int, halfStar: Int, context: Context) {
    for (i in 0 until fullStars) {
        val newStar = ImageView(context)
        val params = LinearLayout.LayoutParams(
            context.resources.getDimensionPixelSize(R.dimen.star_size),
            context.resources.getDimensionPixelSize(R.dimen.star_size)
        )
        newStar.layoutParams = params
        newStar.setImageResource(R.drawable.ic_star_fill)
        newStar.contentDescription = context.getString(R.string.icon)
        parentLayout.addView(newStar)
    }

    if (halfStar == 1) {
        val newHalfStar = ImageView(context)
        val params = LinearLayout.LayoutParams(
            context.resources.getDimensionPixelSize(R.dimen.star_size), // Set the size here
            context.resources.getDimensionPixelSize(R.dimen.star_size)
        )
        newHalfStar.layoutParams = params
        newHalfStar.setImageResource(R.drawable.ic_half_star)
        newHalfStar.contentDescription = context.getString(R.string.icon)
        parentLayout.addView(newHalfStar)
    }
}

interface OnItemClickCallback {
    fun onItemClicked(data: ReviewAllUser)
}
