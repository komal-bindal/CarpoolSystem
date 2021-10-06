package com.example.carpoolsystem.utility

import java.util.regex.Pattern

class RideUtils {
    companion object {
        fun isValidSource(source: String): Boolean {
            val sourcePattern = "[A-Za-z0-9'\\.\\-\\s\\,]"
            val pattern = Pattern.compile(sourcePattern)
            val matcher = pattern.matcher(source)
            return matcher.matches()
        }

        fun isValidDestination(destination: String): Boolean {
            val destinationPattern = "[A-Za-z0-9'\\.\\-\\s\\,]"
            val pattern = Pattern.compile(destinationPattern)
            val matcher = pattern.matcher(destination)
            return matcher.matches()
        }
    }
}