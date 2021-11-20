package com.example.carpoolsystem.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.google.firebase.auth.FirebaseAuth

class DashboardPassenger : AppCompatActivity() {
    private lateinit var passengerProfileLayout: RelativeLayout
    private lateinit var passengerSearchRideLayout: RelativeLayout
    private lateinit var passengerForgetPasswordLayout: RelativeLayout
    private lateinit var passengerFeedbackLayout: RelativeLayout
    private lateinit var passengerLogoutLayout: RelativeLayout
    private lateinit var passengerRequestStatusLayout: RelativeLayout
    private var FEEDBACK_URL = "https://forms.gle/2zD9AoMWGegbTwmg8"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_passenger)

        passengerProfileLayout = findViewById(R.id.passengerProfileLayout)
        passengerSearchRideLayout = findViewById(R.id.passengerSearchRideLayout)
        passengerRequestStatusLayout = findViewById(R.id.passengerRequestStatusLayout)
        passengerForgetPasswordLayout = findViewById(R.id.passengerForgetPasswordLayout)
        passengerFeedbackLayout = findViewById(R.id.passengerFeedbackLayout)
        passengerLogoutLayout = findViewById(R.id.passengerLogoutLayout)


        passengerProfileLayout.setOnClickListener {
            val intent = Intent(this@DashboardPassenger, PassengersProfile::class.java)
            startActivity(intent)
        }
        passengerSearchRideLayout.setOnClickListener {
            val intent = Intent(this@DashboardPassenger, SearchResults::class.java)
            startActivity(intent)
        }
        passengerLogoutLayout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@DashboardPassenger, UsersScreen::class.java)
            startActivity(intent)
            finish()
        }
        passengerForgetPasswordLayout.setOnClickListener {
            val intent = Intent(this@DashboardPassenger, ChangePassword::class.java)
            startActivity(intent)

        }
        passengerRequestStatusLayout.setOnClickListener {
            val intent = Intent(this@DashboardPassenger, PassengersProfile::class.java)
            startActivity(intent)
        }
        passengerFeedbackLayout.setOnClickListener {
            var uri = Uri.parse(FEEDBACK_URL)
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }
}