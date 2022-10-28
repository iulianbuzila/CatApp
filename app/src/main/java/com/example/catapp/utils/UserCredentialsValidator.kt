package com.example.catapp.utils

import android.text.TextUtils
import androidx.core.util.PatternsCompat

object UserCredentialsValidator {
    private const val PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{3,}$"

    fun isEmailValid(email: String): Boolean {
        return !TextUtils.isEmpty(email) && PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        return !TextUtils.isEmpty(password) && PASSWORD_REGEX.toRegex().matches(password)
    }
}