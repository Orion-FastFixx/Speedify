package com.example.speedify.feature_profile.presentation.kendaraan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.TextView
import com.example.speedify.R
import com.example.speedify.databinding.ActivityEditKendaraanBinding
import com.example.speedify.databinding.ActivityEditKendaraanMobilBinding

class EditKendaraanMobilActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityEditKendaraanMobilBinding
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEditKendaraanMobilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Configuration()
    }

    private fun Configuration() {
        // untuk mengatur teks dan nilai awal
        fun setEditText(textView: TextView, value: String) {
            textView.text = value
        }

        // untuk mengatur input type
        fun setEditTextType(editText: EditText, inputType: Int) {
            editText.inputType = inputType
        }

        // Menetapkan isi TextView
        setEditText(binding.editKendaraanMobil.editKendaraan.ket1, "Tipe")
        setEditText(binding.editKendaraanMobil.editKendaraan.ket2, "Merk")
        setEditText(binding.editKendaraanMobil.editKendaraan.ket3, "Tahun")

        // Menetapkan isi untuk EditText
        setEditText(binding.editKendaraanMobil.editKendaraan.EditText1, "Honda")
        setEditText(binding.editKendaraanMobil.editKendaraan.EditText2, "Avanza")
        setEditText(binding.editKendaraanMobil.editKendaraan.EditText3, "2015")

        setEditTextType(binding.editKendaraanMobil.editKendaraan.EditText3, InputType.TYPE_CLASS_NUMBER)
    }
}