package com.example.carpoolsystem

import android.util.Patterns
import java.util.regex.Pattern

class RegistrationUtils {
    companion object {
        fun isValidPassword(password: String): Boolean {
            val passwordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,12}$"
            val pattern = Pattern.compile(passwordPattern)
            val matcher = pattern.matcher(password)
            return matcher.matches()
        }

        fun isValidEmail(emailId: String): Boolean {
            val emailPattern = "^[a-z]+.[a-z]+_[a-z0-9]+@gla.ac.in$"
            val pattern = Pattern.compile(emailPattern)
            val matcher = pattern.matcher(emailId)
            return matcher.matches()
        }

        fun isValidPhoneNumber(phoneNumber: String): Boolean {
            val phoneNumberPattern = "^[0-9]{10}$"
            val pattern = Pattern.compile(phoneNumberPattern)
            val matcher = pattern.matcher(phoneNumber)
            return matcher.matches()
        }
        fun isValidVehicleNumber(vehicleNumber:String):Boolean{
            val vehicleNumberPattern="^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}\$"
            val pattern = Pattern.compile(vehicleNumberPattern)
            val matcher = pattern.matcher(vehicleNumber)
            return matcher.matches()
        }


    }
}