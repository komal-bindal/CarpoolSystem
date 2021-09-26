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
        fun isValidSource(source:String):Boolean{
            val sourcePattern="^[A-Z]"
            val pattern = Pattern.compile(sourcePattern)
            val matcher = pattern.matcher(source)
            return matcher.matches()
        }
        fun isValidDestination(destination:String):Boolean{
            val destinationPattern="^[A-Z]"
            val pattern = Pattern.compile(destinationPattern)
            val matcher = pattern.matcher(destination)
            return matcher.matches()
        }
        fun isValidDate(date:String):Boolean{
            val datePattern="^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d\$"
            val pattern = Pattern.compile(datePattern)
            val matcher = pattern.matcher(date)
            return matcher.matches()
        }
        fun isValidTime(time:String):Boolean{
            val timePattern="(1[012]|[1-9]):[0-5][0-9](\\\\s)?(?i)(am|pm)"
            val pattern = Pattern.compile(timePattern)
            val matcher = pattern.matcher(time)
            return matcher.matches()
        }

    }
}