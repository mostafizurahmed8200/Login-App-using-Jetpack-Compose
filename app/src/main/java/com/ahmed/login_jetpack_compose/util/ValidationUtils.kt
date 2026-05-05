package com.ahmed.login_jetpack_compose.util

object ValidationUtils {
    private val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    fun isValidEmail(email: String): Boolean = EMAIL_REGEX.matches(email.trim())
}