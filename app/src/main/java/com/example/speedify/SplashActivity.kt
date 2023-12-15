package com.example.speedify

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.speedify.core.data.local.UserDataStoreImpl
import com.example.speedify.feature_authentication.presentation.sign_in.SignInActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var userDataStore: UserDataStoreImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScope = CoroutineScope(Dispatchers.Main)
        splashScope?.launch {
            delay(1000) // Display splash screen for 1 seconds

            val userPreferences = userDataStore.getUser()
            if (userPreferences.token.isNullOrEmpty()) {
                Intent(this@SplashActivity, SignInActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            } else {
                Intent(this@SplashActivity, MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        splashScope?.cancel()
    }

    private var splashScope: CoroutineScope? = null
}