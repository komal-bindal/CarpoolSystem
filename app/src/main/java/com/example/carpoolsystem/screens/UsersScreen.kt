package com.example.carpoolsystem.screens

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R

class UsersScreen : AppCompatActivity() {
    private lateinit var buttondriver: Button
    private lateinit var buttonpassenger: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_screen)
        buttondriver = findViewById(R.id.buttonDriver)
        buttonpassenger = findViewById(R.id.buttonPassenger)
        buttondriver.setOnClickListener {
            if (checkForInternet(this)) {
                Toast.makeText(this, "Connected to internet", Toast.LENGTH_SHORT).show()
                val intent = (Intent(this@UsersScreen, SignInScreen::class.java))
                intent.putExtra("User", "Driver")
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Disconnected!Please connect to internet", Toast.LENGTH_SHORT)
                    .show()
            }

        }
        buttonpassenger.setOnClickListener {
            if (checkForInternet(this)) {
                Toast.makeText(this, "Connected to internet", Toast.LENGTH_SHORT).show()
                val intent = (Intent(this@UsersScreen, SignInScreen::class.java))
                intent.putExtra("User", "Passenger")
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Disconnected!Please connect to internet", Toast.LENGTH_SHORT)
                    .show()
            }

        }
    }

    private fun checkForInternet(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

}