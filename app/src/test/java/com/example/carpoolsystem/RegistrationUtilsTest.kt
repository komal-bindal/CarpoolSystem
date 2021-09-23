package com.example.carpoolsystem

import org.junit.Assert
import org.junit.Test

class RegistrationUtilsTest {
    @Test
    fun isValidPassword_SmallCaseAlphabetsOnly_False() {
        val result = RegistrationUtils.isValidPassword("abcdef")
        Assert.assertFalse(result)
    }

    @Test
    fun isValidPassword_CapitalCaseAlphabetsOnly_False() {
        val result = RegistrationUtils.isValidPassword("ABCDEF")
        Assert.assertFalse(result)
    }

    @Test
    fun isValidPassword_LengthLessThan4_False() {
        val result = RegistrationUtils.isValidPassword("A@2")
        Assert.assertFalse(result)
    }

    @Test
    fun isValidPassword_Empty_False() {
        val result = RegistrationUtils.isValidPassword("")
        Assert.assertFalse(result)
    }

    @Test
    fun isValidPassword_ContainsSpace_False() {
        val result = RegistrationUtils.isValidPassword("A 8 u76#")//A 8 u76#
        //ade  67#
        Assert.assertFalse(result)
    }

    @Test
    fun isValidPassword_DigitsOnly_False() {
        var result = RegistrationUtils.isValidPassword("12345")
        Assert.assertFalse(result)
    }

    @Test
    fun isValidPassword_ValidPassword_True() {
        var result = RegistrationUtils.isValidPassword("aA34@")
        Assert.assertTrue(result)
    }

    @Test
    fun isValidPassword_SpecialCharactersOnly_True() {
        val result = RegistrationUtils.isValidPassword("@#%^&+=@")
        Assert.assertFalse(result)
    }

    @Test
    fun isValidPassword_LimitExceed_False() {
        val result = RegistrationUtils.isValidPassword("Abyt5der69hsrecdhbxd@^")
        Assert.assertFalse(result)
    }
}