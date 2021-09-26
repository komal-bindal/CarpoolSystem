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
    @Test
    fun isValidPhoneNumber_LengthLimitExceed_False(){
        val result=RegistrationUtils.isValidPhoneNumber("12233456789234")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidPhoneNumber_LengthLimitLessThanRequired_False(){
        val result=RegistrationUtils.isValidPhoneNumber("12238765")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidPhoneNumber_NumberIsNull_False(){
        val result=RegistrationUtils.isValidPhoneNumber("")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidPhoneNumber_NumberContainsSpaces_False(){
        val result=RegistrationUtils.isValidPhoneNumber(" ")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidPhoneNumber_NumberContainsSpecialCharacters_False(){
        val result=RegistrationUtils.isValidPhoneNumber("123456789#")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidPhoneNumber_NumberContainsAlphabets_False(){
        val result=RegistrationUtils.isValidPhoneNumber("675421234r")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidPhoneNumber_NumberContainsSpacesInBetween_False(){
        val result=RegistrationUtils.isValidPhoneNumber("3214567 98")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidPhoneNumber_NumberContainsZero_False(){
        val result=RegistrationUtils.isValidPhoneNumber("08979809698")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidPhoneNumber_NumberContainsNineOne_False(){
        val result=RegistrationUtils.isValidPhoneNumber("+918979809698")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidEmail_EmailIsEmpty_False(){
        val result=RegistrationUtils.isValidEmail("")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidEmail_EmailContainsSpaces_False(){
        val result=RegistrationUtils.isValidEmail(" ")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidEmail_EmailDoesNotContainsGlaNotation_False(){
        val result=RegistrationUtils.isValidEmail("skritika2000@gmail.com")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidEmail_EmailDoesNotContainsSpecialSymbols_False(){
        val result=RegistrationUtils.isValidEmail("kritkika.sharma_cs19@glaac.in")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidEmail_EmailDoesNotStartWithCapitalLetter_False(){
        val result=RegistrationUtils.isValidEmail("Kritkika.sharma_cs19@glaac.in")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidEmail_EmailDoesNotContainsCapitalLetter_False(){
        val result=RegistrationUtils.isValidEmail("Kritkika.shaRma_cs19@glaac.in")
        Assert.assertFalse(result)
    }
    @Test
    fun isValidEmail_DoesNotContainsEmailUnderscore_False(){
        val result=RegistrationUtils.isValidEmail("Kritkika.sharmacs19@gla.ac.in")
        Assert.assertFalse(result)
    }
}