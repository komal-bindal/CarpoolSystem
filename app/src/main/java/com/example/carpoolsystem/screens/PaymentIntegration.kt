package com.example.carpoolsystem.screens

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.razorpay.Checkout
import org.json.JSONObject

class PaymentIntegration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_integration)
        val amountEditText = findViewById<TextInputEditText>(R.id.amount)
        val process = findViewById<MaterialButton>(R.id.process_payment)



        process.setOnClickListener {

            val amount = amountEditText.text.toString().trim()

            if (amount.isEmpty()) return@setOnClickListener



            startPayment(amount.toInt())

        }


    }

    private fun startPayment(amount: Int) {
        val checkout = Checkout()
        checkout.setKeyID("rzp_test_0Fz5oipur0X9XI")
        try {
            val options = JSONObject()
            options.put("name", "Razorpay Integration")
            options.put("description", "Learning tutorial")
            options.put("theme.color", "#3399cc")
            options.put("currency", "INR")
            options.put("amount", "${(amount.toInt() * 100)}")//pass amount in currency subunits
            options.put("prefill.email", "skritika2000@gmail.com")
            options.put("prefill.contact", "+918979809698")

            checkout.open(this, options)

        } catch (e: Exception) {
            Toast.makeText(this, "Error in payment: " + e.message, Toast.LENGTH_LONG)
                .show()
            e.printStackTrace()
        }

    }

    fun onPaymentSuccess(p0: String?) {
        Toast.makeText(this, "Payment Successful", Toast.LENGTH_SHORT).show()
    }

    fun onPaymentError(p0: Int, p1: String?) {
        Log.d(TAG, "onPaymentError: ${p0}")
        Log.d(TAG, "onPaymentError: ${p1}")


        Toast.makeText(this, "Payment Not Successful", Toast.LENGTH_SHORT).show()
    }
}