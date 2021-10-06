package com.example.carpoolsystem

import com.example.carpoolsystem.utility.VehicleUtils
import org.junit.Assert
import org.junit.Test

class VehicleUtilsTest{
    @Test
    fun isValidCarNumber_ContainsThree_DigitsOnly_False() {
        val result = VehicleUtils.isValidCarNumber("UP-80 AFG 321")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidCarNumberDoesNotContainsSpaces_False() {
        val result = VehicleUtils.isValidCarNumber("UP-80AF 7321")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidCarNumberDoesNotContainsSpacesBeforeNumber_False() {
        val result = VehicleUtils.isValidCarNumber("UP-80 AF7321")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidCarNumberDoesNotContainsSymbolBeforeNumber_False() {
        val result = VehicleUtils.isValidCarNumber("UP80 AF7321")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidCarNumberContainsMoreThanFourNumber_False() {
        val result = VehicleUtils.isValidCarNumber("UP-80 AF 87321")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidCarNumberContainsOnlyDigits_False() {
        val result = VehicleUtils.isValidCarNumber("87-80 96 87321")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidCarNumberContainsOnlyAlphabets_False() {
        val result = VehicleUtils.isValidCarNumber("UP-EY uh uhdf")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidCarNumberIsEmpty_False() {
        val result = VehicleUtils.isValidCarNumber("")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidCarNumberContainsSpaces_False() {
        val result = VehicleUtils.isValidCarNumber("  ")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidCarNumberContainsSpecialCharacter_False() {
        val result = VehicleUtils.isValidCarNumber("UP,80 AF.7321")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidCarModelIsEmpty_False() {
        val result = VehicleUtils.isValidCarModel("")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidCarModelHaveSpaces_False() {
        val result = VehicleUtils.isValidCarModel("  ")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidCarModelContainsOnlyDigits_False() {
        val result = VehicleUtils.isValidCarModel("6753356")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidCarModelContainsSpecialCharacters_False() {
        val result = VehicleUtils.isValidCarModel("Wagoner.")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidCarModelContainsOnlySpecialCharacters_False() {
        val result = VehicleUtils.isValidCarModel("+;'.")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidCarModelIsBlank_False() {
        val result = VehicleUtils.isValidCarModel("")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidCarModelHasSpaces_False() {
        val result = VehicleUtils.isValidCarModel("  ")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidCarModelContainOnlyDigits_False() {
        val result = VehicleUtils.isValidCarModel("6753356")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidCarModelContainSpecialCharacters_False() {
        val result = VehicleUtils.isValidCarModel("Wagoner.")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidCarModelContainOnlySpecialCharacters_False() {
        val result = VehicleUtils.isValidCarModel("+;'.")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidLicenseContainOnlySpecialCharacters_False() {
        val result = VehicleUtils.isValidLicenseNumber("+;'.")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidLicenseContainMoreThan13Characters_False() {
        val result = VehicleUtils.isValidLicenseNumber("MH 14 2011 00628213 ")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidLicenseDoesNotContainStateCodeAndBranchCode_False() {
        val result = VehicleUtils.isValidLicenseNumber("2011 0062821")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidLicenseDoesNotContainYearOfIssue_False() {
        val result = VehicleUtils.isValidLicenseNumber("MH 14  0062821")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidLicenseDoesNotContainProfileId_False() {
        val result = VehicleUtils.isValidLicenseNumber("MH 14 2012")
        Assert.assertFalse(result)
    }


}