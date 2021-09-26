package com.example.carpoolsystem

import java.util.regex.Pattern

class RideUtils {
    companion object{
        fun isValidSource(source:String):Boolean{
            val sourcePattern="^[A-Za-z0-9\\s]"
            val pattern = Pattern.compile(sourcePattern)
            val matcher = pattern.matcher(source)
            return matcher.matches()
        }
        fun isValidDestination(destination:String):Boolean{
            val destinationPattern="^[A-Za-z0-9\\s]"
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