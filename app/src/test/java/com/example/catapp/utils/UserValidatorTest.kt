package com.example.catapp.utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UserValidatorTest {

    @Test
    fun testUserValidatorClass() {
        assertEquals(false, UserCredentialsValidator.isEmailValid("Test"))
        assertEquals(false, UserCredentialsValidator.isEmailValid("test1234"))
        assertEquals(false, UserCredentialsValidator.isEmailValid("test@t123"))
        assertEquals(true, UserCredentialsValidator.isEmailValid("test@test.com"))

        assertEquals(false, UserCredentialsValidator.isPasswordValid("test"))
        assertEquals(true, UserCredentialsValidator.isPasswordValid("test3"))
        assertEquals(false, UserCredentialsValidator.isPasswordValid("Test"))
    }
}