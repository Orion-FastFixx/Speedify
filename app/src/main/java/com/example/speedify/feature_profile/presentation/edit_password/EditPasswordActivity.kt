package com.example.speedify.feature_profile.presentation.edit_password

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.speedify.databinding.ActivityEditPasswordBinding
import com.example.speedify.feature_profile.presentation.setting.SettingActivity

class EditPasswordActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityEditPasswordBinding
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEditPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Configuration()
    }

    private fun Configuration() {
        // untuk mengatur teks dan nilai awal
        fun setEditText(textView: TextView, value: String) {
            textView.text = value
        }

        // Menetapkan isi TextView
        setEditText(binding.editPassword.ket1, "Kata Sandi Lama")
        setEditText(binding.editPassword.ket2, "Kata Sandi Baru")
        setEditText(binding.editPassword.ket3, "Konfirmasi Sandi Baru")

        // Menetapkan isi untuk EditText
        setEditText(binding.editPassword.EditText1, "Hafiz1907")
        setEditText(binding.editPassword.EditText2, "muhammadhafis10")
        setEditText(binding.editPassword.EditText3, "muhammadhafis10")


        //  Pindah ke Profile kembali
        binding.btnSvPassword.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        //Pindah ke kembali K Profile
        binding.icBack.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
    }
}