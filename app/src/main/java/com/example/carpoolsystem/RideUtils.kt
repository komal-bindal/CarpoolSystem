package com.example.carpoolsystem

import java.util.regex.Pattern

class RideUtils {
    companion object{
        fun isValidSource(source:String):Boolean{
            val sourcePattern="[A-Za-z0-9'\\.\\-\\s\\,]"
            val pattern = Pattern.compile(sourcePattern)
            val matcher = pattern.matcher(source)
            return matcher.matches()
        }
        fun isValidDestination(destination:String):Boolean{
            val destinationPattern="[A-Za-z0-9'\\.\\-\\s\\,]"
            val pattern = Pattern.compile(destinationPattern)
            val matcher = pattern.matcher(destination)
            return matcher.matches()
        }
        fun isValidDate(date:String):Boolean{
            val datePattern="^\\\\d{2}-\\\\d{2}-\\\\d{4}\$"
            val pattern = Pattern.compile(datePattern)
            val matcher = pattern.matcher(date)
            return matcher.matches()
        }
        fun isValidTime(time:String):Boolean{
            val timePattern="([01]?[0-9]|2[0-3]):[0-5][0-9]"
            val pattern = Pattern.compile(timePattern)
            val matcher = pattern.matcher(time)
            return matcher.matches()
        }
    }
}