package com.example.carpoolsystem.screens

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignupScreen1 : AppCompatActivity() {

    private var firebaseAuth: FirebaseAuth? = null

    private val USERNAME_ERROR =
        "Name should not be empty and can contain only alphabets and spaces"
    private val PASSWORD_ERROR =
        "Password should contain at least one upper case letter, lower case letter, number, and special characters(@#\$%^&+=!)"
    private val EMAIL_ID_ERROR = "Enter your GLA Email address"

    private lateinit var passwordEditText: EditText
    private lateinit var emailIdEditText: EditText
    private lateinit var nameEditText: EditText
    private lateinit var nextButton: Button
    private lateinit var OTPSignUpButton: Button
    private lateinit var progressDialog: ProgressDialog
    private val UID = "uid"
    private val NAME = "name"
    private val EMAIL_ID = "emailId"
    private val USER = "user"
    private val PHONE_NUMBER = "phoneNumber"
    private val USERS_COLLECTION = "users"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_screen1)

        firebaseAuth = FirebaseAuth.getInstance()

        passwordEditText = findViewById(R.id.editTextPassword)
        emailIdEditText = findViewById(R.id.editTextEmail)
        nameEditText = findViewById(R.id.editTextName)
        nextButton = findViewById(R.id.buttonNext)
        OTPSignUpButton = findViewById(R.id.buttonNext2)

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading")
        progressDialog.setMessage("Please wait...")

        val intent = intent
        val user = intent.getStringExtra("User")

        OTPSignUpButton.setOnClickListener {
            val intent = Intent(this@SignupScreen1, SignupScreen2PhoneNumber::class.java)
            intent.putExtra("User", user)
            startActivity(intent)
        }

        nextButton.setOnClickListener {
            register()
            progressDialog.show()
        }

        passwordEditText.addTextChangedListener(
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
                        if (RegistrationUtils.isValidPassword(s.toString())) {
                            passwordEditText.error = null

                        } else {
                            passwordEditText.error = PASSWORD_ERROR
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {

                }
            })

        emailIdEditText.addTextChangedListener(
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
                        if (RegistrationUtils.isValidEmail(p0.toString())) {
                            emailIdEditText.error = null
                        } else {
                            emailIdEditText.error = EMAIL_ID_ERROR
                        }
                    }
                }

                override fun afterTextChanged(p0: Editable?) {}
            })

        nameEditText.addTextChangedListener(
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
                        if (RegistrationUtils.isValidUserName(p0.toString())) {
                            nameEditText.error = null
                        } else {
                            nameEditText.error = USERNAME_ERROR
                        }
                    }
                }

                override fun afterTextChanged(p0: Editable?) {}
            })
    }

    fun sendEmail() {
        val firebaseUser = firebaseAuth?.currentUser
        firebaseUser?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    this,
                    "Verification mail send. Please verify your email",
                    Toast.LENGTH_SHORT
                ).show()
                firebaseAuth?.signOut()
                finish()
                val intent = Intent(this, SignInScreen::class.java)
                intent.putExtra("name", nameEditText.text.toString())
                startActivity(intent)
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

    @SuppressLint("LogNotTimber")
    fun register() {
        val emailId: String = emailIdEditText.text.toString()
        val password: String = passwordEditText.text.toString()
        val name: String = nameEditText.text.toString()
        if (emailId.isEmpty() || password.isEmpty() || name.isEmpty() || !RegistrationUtils.isValidPassword(
                password
            ) || !RegistrationUtils.isValidEmail(emailId) || !RegistrationUtils.isValidUserName(name)
        ) {
            progressDialog.hide()
            Toast.makeText(this, "Please fill all the given fields correctly", Toast.LENGTH_SHORT)
                .show()
        } else {
            firebaseAuth?.createUserWithEmailAndPassword(emailId, password)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        progressDialog.hide()
                        val intent = intent
                        val selectedUser = intent.getStringExtra("User").toString()
                        val firebaseUser = firebaseAuth?.currentUser
                        val user = createUserHasMap(
                            firebaseUser?.uid!!.toString(),
                            name,
                            emailId,
                            selectedUser,
                            ""
                        )
                        Log.d("user", selectedUser)
                        addUserInDatabase(firebaseUser, user)
                        sendEmail()
                    } else {
                        progressDialog.hide()
                        Log.e("error", task.exception.toString())
                        Toast.makeText(
                            this,
                            "Some error occurred." + task.exception.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    @SuppressLint("LogNotTimber")
    private fun addUserInDatabase(firebaseUser: FirebaseUser, user: HashMap<String, String>) {
        val db = Firebase.firestore
        db.collection(USERS_COLLECTION).document(firebaseUser.uid)
            .set(user)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    "database",
                    "DocumentSnapshot "
                )
            }.addOnFailureListener { e ->
                Log.w("error", "Error adding documnent", e)
            }
    }


    private fun createUserHasMap(
        uid: String,
        name: String,
        emailId: String,
        selectedUser: String,
        phoneNumber: String
    ): HashMap<String, String> {
        return hashMapOf(
            UID to uid,
            NAME to name,
            EMAIL_ID to emailId,
            USER to selectedUser,
            PHONE_NUMBER to phoneNumber
        )
    }
}