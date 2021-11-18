package com.example.carpoolsystem.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ChangePassword : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var buttonReset: Button
    private lateinit var editTextForEmailVerifiaction: EditText
    private lateinit var textViewResponse: TextView
    private lateinit var imageViewBackButton: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        buttonReset = findViewById(R.id.buttonResetId)
        editTextForEmailVerifiaction = findViewById(R.id.editTextEnterNewPassword2)
        textViewResponse = findViewById(R.id.textViewdisplayMessage)
        imageViewBackButton = findViewById(R.id.imageViewback)
        buttonReset.setOnClickListener {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
            if (editTextForEmailVerifiaction.text.toString().isNullOrEmpty())
                textViewResponse.text = "Email Address is not provided"
            else {
                auth.sendPasswordResetEmail(
                    editTextForEmailVerifiaction.text.toString()
                )
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val user = auth.currentUser
                            textViewResponse.text = "Reset Password Link is mailed"
                        } else
                            textViewResponse.text = "Password Reset mail could not be sent"
                    }
            }
        }
        imageViewBackButton.setOnClickListener {
            val intent = Intent(this, DashboardPassenger::class.java)
            startActivity(intent)
            finish()
        }
    }
}
