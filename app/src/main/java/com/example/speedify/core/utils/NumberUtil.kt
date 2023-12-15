package com.example.speedify.core.utils

import java.text.NumberFormat
import java.util.Locale

fun Int?.currencyFormat(): String {
    return if (this == null) {
        "Rp0"
    } else {
        val price = this.toLong()
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        numberFormat.maximumFractionDigits = 0
        numberFormat.format(price).toString()
    }
}

//create currencyFormat without Rp
fun Int?.currencyFormatWithoutRp(): String {
    return if (this == null) {
        "0"
    } else {
        val price = this.toLong()
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        numberFormat.maximumFractionDigits = 0
        numberFormat.format(price).toString().replace("Rp", "")
    }
}