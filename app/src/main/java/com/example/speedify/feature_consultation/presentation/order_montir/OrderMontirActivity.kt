package com.example.speedify.feature_consultation.presentation.order_montir

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.speedify.MainActivity
import com.example.speedify.R
import com.example.speedify.core.utils.toCamelCase
import com.example.speedify.databinding.ActivityOrderMontirBinding
import com.example.speedify.feature_consultation.data.model.OrderMontirServiceResponse
import com.example.speedify.feature_consultation.data.model.ServicesItemMontir
import com.example.speedify.feature_payment.PaymentMethodsActivity
import com.example.speedify.core.utils.currencyFormat
import com.example.speedify.core.utils.currencyFormatWithoutRp


class OrderMontirActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityOrderMontirBinding

    private val binding get() = _binding

    companion object {
        const val PAYMENT_METHOD_NAME = "key_payment_method_name"
        const val BALANCE = "key_balance"
        const val EXTRA_ORDER_DATA = "EXTRA_ORDER_DATA"
    }
}
