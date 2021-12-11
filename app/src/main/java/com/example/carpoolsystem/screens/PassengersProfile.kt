package com.example.carpoolsystem.screens

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.carpoolsystem.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class PassengersProfile : AppCompatActivity() {
    private lateinit var phonenumberChange: TextView
    private lateinit var passwordChange: TextView
    private lateinit var logout: Button
    private lateinit var name: TextView
    private lateinit var emailTextView: TextView
    private lateinit var phone: TextView
    private lateinit var deleteAccountButton: Button
    private val USERS_COLLECTION = "users"
    private val UID = "uid"
    private lateinit var EmailVerify:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_passengers_profile)

        phonenumberChange = findViewById(R.id.phonenumberchangepassenger)
        passwordChange = findViewById(R.id.passwordchangepassenger)
        logout = findViewById(R.id.buttonlogoutpassenger)
        name = findViewById(R.id.fullNamePassenger)
        emailTextView = findViewById(R.id.emailofpassenger)
        phone = findViewById(R.id.phoneNumberTextView)
        deleteAccountButton = findViewById(R.id.deleteAccountPassenger)
        EmailVerify = findViewById(R.id.Emailverify)

        fetchDetailsFromDatabase()
//        val addEmailDialog = AlertDialog.Builder(this)
//            .setTitle("Verify Your Email")
//            .setMessage("Plz verify the email send to your Email Id")
//            .setIcon(R.drawable.ic_add_email_background)
//            .setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
//
//            }
//            .setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
//                Toast.makeText(this, "Email Verification is mandatory", Toast.LENGTH_SHORT).show()
//            }.create()
//        EmailVerify.setOnClickListener {
//
//            addEmailDialog.show()
//        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirm")
        builder.setMessage("Are you sure?")

        builder.setPositiveButton(
            "YES"
        ) { dialog, which ->
            val user = FirebaseAuth.getInstance().currentUser
            user?.delete()?.addOnCompleteListener { task ->
                Toast.makeText(this, "account deleted successfully", Toast.LENGTH_SHORT).show()
                deleteAccountFromDatabase(user)
                startActivity(Intent(this, UsersScreen::class.java))
                finish()
            }?.addOnFailureListener { e ->
                Toast.makeText(this, e.message.toString(), Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }

        builder.setNegativeButton(
            "NO"
        ) { dialog, which ->
            dialog.dismiss()
        }

        deleteAccountButton.setOnClickListener {
            val alert: AlertDialog = builder.create()
            alert.show()
        }

        emailTextView.setOnClickListener {
            if (emailTextView.text.toString().equals("Add your email id")) {
                val intent = Intent(this, EmailIdEmpty::class.java)
                startActivity(intent)
                if (emailTextView.text.toString().length == 0) {
                    EmailVerify.visibility = View.GONE
                } else {
                    EmailVerify.visibility = View.VISIBLE
                }
                finish()
            }
        }

        phonenumberChange.setOnClickListener {
            val intent = Intent(this@PassengersProfile, AddNewPhoneNumber::class.java)
            if (phonenumberChange.text.toString().equals("Add phone number")) {
                Log.d("Passenger", "add")
                intent.putExtra("Phone", "add")
            } else {
                Log.d("Passenger", "change")
                intent.putExtra("phone", "change")
            }
            startActivity(intent)
            finish()
        }

        passwordChange.setOnClickListener {
            val intent = Intent(this@PassengersProfile, ChangePassword::class.java)
            startActivity(intent)
        }

        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@PassengersProfile, UsersScreen::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun deleteAccountFromDatabase(user: FirebaseUser) {
        val db = FirebaseFirestore.getInstance()
        val docReference = db.collection(USERS_COLLECTION)
            .whereEqualTo(UID, user.uid.toString())
        docReference.get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val list: List<DocumentSnapshot> =
                        querySnapshot.documents
                    for (d in list) {
                        d.reference.delete()
                    }
                }
            }.addOnFailureListener { e ->
                Toast.makeText(this, e.message.toString(), Toast.LENGTH_SHORT).show()
            }

    }

    private fun fetchDetailsFromDatabase() {
        val firebaseUser = FirebaseAuth.getInstance().currentUser
        val docRef = FirebaseFirestore.getInstance().collection("users")
            .whereEqualTo("uid", firebaseUser?.uid.toString())
        docRef.get().addOnSuccessListener { querySnapshot ->
            if (querySnapshot.isEmpty == false) {
                var list = querySnapshot.documents
                for (item in list) {
                    val userName = item.data?.get("name").toString()
                    val emailId = item.data?.get("emailId").toString()
                    val phoneNumber = item.data?.get("phoneNumber").toString()
                    if (!userName.isEmpty()) {
                        name.text = userName
                    }
                    if (emailId.isEmpty() == false) {
                        emailTextView.text = emailId
                    }
                    if (phoneNumber.isEmpty() == false) {
                        phone.text = phoneNumber
                    } else {
                        phonenumberChange.text = "Add phone number"
                    }
                }
            }
        }
    }
}