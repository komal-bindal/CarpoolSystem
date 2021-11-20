package com.example.carpoolsystem.screens

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class EmailIdEmpty : AppCompatActivity() {
    private lateinit var idEditText2: EditText
    private lateinit var editTextPasswordValue: EditText
    private lateinit var loginButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_id_empty)
        idEditText2 = findViewById(R.id.EnterEmailIdentity)
        editTextPasswordValue = findViewById(R.id.EnterpasswordPassword)
        loginButton = findViewById(R.id.login_Button)

        val email = idEditText2.text.toString()
        val password = editTextPasswordValue.text.toString()

        loginButton.setOnClickListener {
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show()
            val credential = EmailAuthProvider.getCredential(email, password)
            val auth = FirebaseAuth.getInstance()
            auth.currentUser!!.linkWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = task.result?.user
                        Toast.makeText(
                            applicationContext,
                            "EmailId added successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }.addOnFailureListener(this) { a ->
                    Toast.makeText(this, "fail to create", Toast.LENGTH_SHORT).show()
                }
        }
    }
}