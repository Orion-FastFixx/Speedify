package com.example.speedify.feature_profile.presentation.kendaraan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.TextView
import com.example.speedify.databinding.ActivityEditKendaraanBinding

class EditKendaraanMotorActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityEditKendaraanBinding
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEditKendaraanBinding.inflate(layoutInflater)
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
        setEditText(binding.editKendaraan.ket1, "Tipe")
        setEditText(binding.editKendaraan.ket2, "Merk")
        setEditText(binding.editKendaraan.ket3, "Tahun")

        // Menetapkan isi untuk EditText
        setEditText(binding.editKendaraan.EditText1, "Honda")
        setEditText(binding.editKendaraan.EditText2, "Vario")
        setEditText(binding.editKendaraan.EditText3, "2015")

        setEditTextType(binding.editKendaraan.EditText3, InputType.TYPE_CLASS_NUMBER)


        binding.icBack.setOnClickListener {
            val intent = Intent(this, DataKendaraanActivity::class.java)
            startActivity(intent)
        }
    }
}