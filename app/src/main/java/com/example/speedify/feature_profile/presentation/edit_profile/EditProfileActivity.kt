package com.example.speedify.feature_profile.presentation.edit_profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.TextView
import com.example.speedify.databinding.ActivityEditProfileBinding
import com.example.speedify.feature_profile.presentation.ProfileFragment

class EditProfileActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityEditProfileBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEditProfileBinding.inflate(layoutInflater)
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
        setEditText(binding.editProfile.ket1, "Nama Lengkap")
        setEditText(binding.editProfile.ket2, "Email")
        setEditText(binding.editProfile.ket3, "No Hp")

        // Menetapkan isi untuk EditText
        setEditText(binding.editProfile.EditText1, "Muhammad Hafiz")
        setEditText(binding.editProfile.EditText2, "muhammadhafiz10@gmail.com")
        setEditText(binding.editProfile.EditText3, "081392711890")

        // Menetapkan input type untuk EditText
        setEditTextType(binding.editProfile.EditText3, InputType.TYPE_CLASS_PHONE)

        //  Pindah ke Profile kembali
        binding.btnSvProfile.setOnClickListener {
            val intent = Intent(this, ProfileFragment::class.java)
            startActivity(intent)
        }

        //Pindah ke kembali K Profile
        binding.icBack.setOnClickListener {
            val intent = Intent(this, ProfileFragment::class.java)
            startActivity(intent)
        }
    }

}