package com.example.carpoolsystem.screens

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.carpoolsystem.R
import com.ramotion.paperonboarding.PaperOnboardingFragment
import com.ramotion.paperonboarding.PaperOnboardingPage
import java.util.*

class Animation : AppCompatActivity() {
    private lateinit var getStarted: Button
    private lateinit var skip: Button
    private var fragmentManager: FragmentManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
        getStarted = findViewById(R.id.buttongetStarted)
        skip = findViewById(R.id.buttonskipnow)
        skip.setOnClickListener {
            startActivity(Intent(this, WaitingActivity::class.java))

        }
        getStarted.setOnClickListener {
            startActivity(Intent(this, UsersScreen::class.java))
            finish()
        }
        fragmentManager = supportFragmentManager
        val paperOnboardingFragment = PaperOnboardingFragment.newInstance(
            dataforOnboarding
        )
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.add(R.id.frame_layout, paperOnboardingFragment)
        fragmentTransaction.commit()
    }

    private val dataforOnboarding: ArrayList<PaperOnboardingPage>
        private get() {
            val source = PaperOnboardingPage(
                "Find Carpool , Convenient , Faster , Affordable",
                "Think Eco friendly be Pocket friendly",
                Color.parseColor("#ffffff"),
                R.drawable.onboarding3,
                0
            )
            val source1 = PaperOnboardingPage(
                "Offer Carpool ,Your Route, Your Time, Your Fare",
                "Reduce CO2 , Save Money , Grow network",
                Color.parseColor("#ffffff"),
                R.drawable.onboarding2,
                0

            )
            val source2 = PaperOnboardingPage(
                "Why Ride & Share?",
                "Whether you have a vehicle or not, join Ride & Share and save up to 50% of travel costs.",
                Color.parseColor("#ffffff"),
                R.drawable.onboarding4,
                0
            )
            val elements = ArrayList<PaperOnboardingPage>()
            elements.add(source)
            elements.add(source1)
            elements.add(source2)
            return elements
        }

}
