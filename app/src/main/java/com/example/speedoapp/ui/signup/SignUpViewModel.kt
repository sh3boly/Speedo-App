package com.example.speedoapp.ui.signup

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpViewModel : ViewModel() {

    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError: StateFlow<String?> = _passwordError.asStateFlow()

    private val _emailError = MutableStateFlow<String?>(null)
    val emailError: StateFlow<String?> = _emailError.asStateFlow()

    private val _nameError = MutableStateFlow<String?>(null)
    val nameError: StateFlow<String?> = _emailError.asStateFlow()

    fun validatePassword(password: String) {
        _passwordError.value = when {
            password.length < 6 -> "Password must be at least 6 characters long"
            !password.any { it.isLowerCase() } -> "Password must contain at least one lowercase letter"
            !password.any { it.isUpperCase() } -> "Password must contain at least one uppercase letter"
            !password.any { !it.isLetterOrDigit() } -> "Password must contain at least one special character"
            else -> null
        }
    }

    fun validateEmail(email: String) {
        val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        _emailError.value =
            if (email.matches(emailRegex.toRegex())) null else "Please enter a valid email address"
    }

    fun validateName(name: String) {
        _nameError.value = if (name.isEmpty()) "Please enter your name" else null
    }
}
