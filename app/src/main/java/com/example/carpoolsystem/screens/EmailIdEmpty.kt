package com.example.carpoolsystem.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.example.carpoolsystem.utility.RegistrationUtils
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class EmailIdEmpty : AppCompatActivity() {
    private lateinit var emailIdEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var nameEditText: EditText
    private val USERNAME_ERROR =
        "Name should not be empty and can contain only alphabets and spaces"
    private val PASSWORD_ERROR =
        "Password length should be 6"
    private val EMAIL_ID_ERROR = "Enter your GLA Email address"
    private val UID = "uid"
    private val NAME = "name"
    private val EMAIL_ID = "emailId"
    private val USERS_COLLECTION = "users"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_id_empty)
        emailIdEditText = findViewById(R.id.EnterEmailIdentity)
        passwordEditText = findViewById(R.id.EnterpasswordPassword)
        loginButton = findViewById(R.id.login_Button)
        nameEditText = findViewById(R.id.enterName)


        nameEditText.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int, count: Int, after: Int
                ) {

                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int, before: Int, count: Int
                ) {
                    s?.apply {
                        if (RegistrationUtils.isValidUserName(s.toString())) {
                            nameEditText.error = null

                        } else {
                            nameEditText.error = USERNAME_ERROR
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })

        emailIdEditText.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int, count: Int, after: Int
                ) {

                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int, before: Int, count: Int
                ) {
                    s?.apply {
                        if (RegistrationUtils.isValidEmail(s.toString())) {
                            emailIdEditText.error = null

                        } else {
                            emailIdEditText.error = EMAIL_ID_ERROR
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })

        passwordEditText.addTextChangedListener(
            object : TextWatcher {
                override fun beforeTextChanged(
                    p0: CharSequence?,
                    p1: Int, p2: Int, p3: Int
                ) {
                }

                override fun onTextChanged(
                    p0: CharSequence?,
                    p1: Int, p2: Int, p3: Int
                ) {
                    p0?.apply {
                        if (RegistrationUtils.isValidPassword(p0.toString())) {
                            passwordEditText.error = null
                        } else {
                            passwordEditText.error = PASSWORD_ERROR
                        }
                    }
                }

                override fun afterTextChanged(p0: Editable?) {}
            })

        loginButton.setOnClickListener {
            val email = emailIdEditText.text.toString()
            val password = passwordEditText.text.toString()
            val name = nameEditText.text.toString()
            if (email.isEmpty() || password.isEmpty() || name.isEmpty() || !RegistrationUtils.isValidUserName(
                    name
                ) || !RegistrationUtils.isValidEmail(email) || !RegistrationUtils.isValidPassword(
                    password
                )
            ) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val credential = EmailAuthProvider.getCredential(email, password)
            var auth = FirebaseAuth.getInstance()
            auth.currentUser!!.linkWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        sendEmail(auth)
                        addEmailtoDatabase(email, name)
                        emailIdEditText.text.clear()
                        passwordEditText.text.clear()
                        passwordEditText.error = null
                        emailIdEditText.error = null
                    } else {
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }.addOnFailureListener(this) { a ->
                    Toast.makeText(this, a.message.toString(), Toast.LENGTH_SHORT).show()
                }
        }
    }

    fun sendEmail(firebaseAuth: FirebaseAuth) {
        val firebaseUser = firebaseAuth.currentUser
        firebaseUser?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    this,
                    "Verification mail send. Please verify your email now",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Error occurred." + task.exception.toString(),
                    Toast.LENGTH_SHORT
                ).show()
                Log.e("error", task.exception.toString())
            }
        }
    }

    private fun addEmailtoDatabase(email: String, name: String) {
        val firebaseUser = FirebaseAuth.getInstance().currentUser

        val docReference = FirebaseFirestore.getInstance().collection(USERS_COLLECTION)
            .whereEqualTo(UID, firebaseUser?.uid!!.toString())

        docReference.get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val list: List<DocumentSnapshot> =
                        querySnapshot.documents
                    for (d in list) {
                        d.reference.update(EMAIL_ID, email)
                        d.reference.update(NAME, name)
                    }
                }
            }.addOnFailureListener { e ->
                Toast.makeText(this, e.message.toString(), Toast.LENGTH_SHORT).show()
            }
    }
}