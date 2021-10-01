package com.example.carpoolsystem

import java.util.regex.Pattern

class VehicleUtils {
    companion object{
        fun isValidCarNumber(carNumber: String): Boolean {
            val carNumberPattern = "^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}\$"
            val pattern = Pattern.compile(carNumberPattern)
            val matcher = pattern.matcher(carNumber)
            return matcher.matches()
        }
        fun isValidCarModel(carModel: String): Boolean {
            val carModelPattern = "^[A-Za-z]\\w{2,29}\$"
            val pattern = Pattern.compile(carModelPattern)
            val matcher = pattern.matcher(carModel)
            return matcher.matches()
        }
        fun isValidCarMake(carMake: String): Boolean {
            val carMakePattern = "^[A-Za-z]\\w{2,29}\$"
            val pattern = Pattern.compile(carMakePattern)
            val matcher = pattern.matcher(carMake)
            return matcher.matches()
        }
        fun isValidLicenseNumber(LicenseNumber: String): Boolean {
            val LicenseNumberPattern = "^(([A-Z]{2}[0-9]{2})( )|([A-Z]{2}-[0-9]{2}))((19|20)[0-9][0-9])[0-9]{7}\$"
            val pattern = Pattern.compile(LicenseNumberPattern)
            val matcher = pattern.matcher(LicenseNumber)
            return matcher.matches()
        }
    }
}