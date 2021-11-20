package com.example.carpoolsystem.screens

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R

class EmailIdEmpty : AppCompatActivity() {
    private lateinit var idEditText2: EditText
    private lateinit var editTextPasswordValue: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_id_empty)
        idEditText2 = findViewById(R.id.EnterEmailIdentity)
        editTextPasswordValue = findViewById(R.id.EnterpasswordPassword)
        /*
        val email = idEditText2.text.toString()
        val password = editTextPasswordValue.text.toString()

        val credential = EmailAuthProvider.getCredential(email, password)
        val auth = FirebaseAuth.getInstance()
        auth.currentUser!!.linkWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    Toast.makeText(applicationContext, "EmailId added successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
            */

    }
}