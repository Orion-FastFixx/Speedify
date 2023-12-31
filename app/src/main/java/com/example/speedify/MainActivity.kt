package com.example.speedify

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.speedify.core.data.local.UserDataStoreImpl
import com.example.speedify.core.data.remote.ApiConfig
import com.example.speedify.databinding.ActivityMainBinding
import com.example.speedify.feature_authentication.presentation.sign_in.SignInActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var userDataStore: UserDataStoreImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            val userPreferences = userDataStore.getUser()
            ApiConfig.TOKEN = userPreferences.token ?: ""
        }

        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val navView: BottomNavigationView = binding.navView

        val navController =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main)!!
                .findNavController()
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_education,
                R.id.navigation_consultation,
                R.id.navigation_activity,
                R.id.navigation_setting
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onResume() {
        super.onResume()
        checkUserSession()
    }

    private fun checkUserSession() {
        lifecycleScope.launch {
            val userPreferences = userDataStore.getUser()
            if (userPreferences.token.isNullOrEmpty()) {
                // User is not logged in, redirect to SignInActivity
                redirectToSignIn()
            }
            // else, user is logged in, continue in this activity
        }
    }

    private fun redirectToSignIn() {
        val intent = Intent(this, SignInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }
}