package com.example.speedify.feature_activity.presentation.detail_pesanan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.speedify.R

class DetailPesananActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pesanan)
    }

    companion object {
        const val EXTRA_PESANAN_ID = "PESANAN_ID"
    }
}