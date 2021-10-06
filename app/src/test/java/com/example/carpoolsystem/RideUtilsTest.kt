package com.example.carpoolsystem

import com.example.carpoolsystem.utility.RideUtils
import org.junit.Assert
import org.junit.Test

class RideUtilsTest{
    @Test
    fun isValidSourceContainOnlySpecialCharacters_False() {
        val result = RideUtils.isValidSource("+;'.")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidSourceContainOnlyDigits_False() {
        val result = RideUtils.isValidSource("1235678")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidSourceContainSpaces_False() {
        val result = RideUtils.isValidSource("  ")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidSourceIsBlank_False() {
        val result = RideUtils.isValidSource("")
        Assert.assertFalse(result)
    }

    @Test
    fun isValidDestinationContainOnlySpecialCharacters_False() {
        val result = RideUtils.isValidDestination("+;'.")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidDestinationContainOnlyDigits_False() {
        val result = RideUtils.isValidDestination("1235678")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidDestinationContainSpaces_False() {
        val result = RideUtils.isValidDestination("  ")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidDestinationIsEmpty_False() {
        val result = RideUtils.isValidDestination("")
        Assert.assertFalse(result)
    }
}