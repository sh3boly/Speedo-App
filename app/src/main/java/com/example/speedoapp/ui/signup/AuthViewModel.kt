package com.example.speedoapp.ui.signup

import TokenManager
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedoapp.api.RetrofitFactory
import com.example.speedoapp.model.LoginRequest
import com.example.speedoapp.model.LoginStatus
import com.example.speedoapp.model.RegisterRequest
import com.example.speedoapp.model.RegisterStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError: StateFlow<String?> = _passwordError.asStateFlow()

    private val _emailError = MutableStateFlow<String?>(null)
    val emailError: StateFlow<String?> = _emailError.asStateFlow()

    private val _nameError = MutableStateFlow<String?>(null)
    val nameError: StateFlow<String?> = _nameError.asStateFlow()

    fun validatePassword(password: String): Boolean {
        _passwordError.value = when {
            password.length < 6 -> "Password must be at least 6 characters long"
            !password.any { it.isLowerCase() } -> "Password must contain at least one lowercase letter"
            !password.any { it.isUpperCase() } -> "Password must contain at least one uppercase letter"
            !password.any { !it.isLetterOrDigit() } -> "Password must contain at least one special character"
            else -> null
        }
        if (_passwordError.value == null)
            return true
        else return false
    }

    fun validateEmail(email: String): Boolean {
        val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        _emailError.value =
            if (email.matches(emailRegex.toRegex())) null else "Please enter a valid email address"
        if (_emailError.value == null)
            return true
        else return false
    }

    fun validateName(name: String): Boolean {
        _nameError.value = if (name.isEmpty()) "Please enter your name" else null
        if (_nameError.value == null)
            return true
        else return false
    }

    private val _registerStatus = MutableStateFlow<RegisterStatus?>(null)
    val registerStatus: StateFlow<RegisterStatus?>  = _registerStatus.asStateFlow()

    fun register(name: String, email: String, password: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = RegisterRequest(name, email, password)
                val response = RetrofitFactory.authApi.register(request)

                if (response.isSuccessful) {
                    val registerResponse = response.body()
                    if (registerResponse != null) {
                        _registerStatus.value = (RegisterStatus.Success(registerResponse))
                    } else {
                        _registerStatus.value = (RegisterStatus.Error("Empty response body"))
                    }
                } else {
                    _registerStatus.value = (RegisterStatus.Error("Error: ${response.code()} ${response.message()}"))
                }
            } catch (e: Exception) {
                _registerStatus.value = (RegisterStatus.Error("Exception: ${e.localizedMessage}"))
            }
        }
    }
    fun resetRegisterStatus(){
        _registerStatus.value = null
    }

    private val _loginStatus = MutableStateFlow<LoginStatus?>(null)
    val loginStatus: StateFlow<LoginStatus?> = _loginStatus.asStateFlow()

    fun login(email: String, password: String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = LoginRequest(email, password)
                val response = RetrofitFactory.authApi.login(request)

                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        _loginStatus.value = (LoginStatus.Success(loginResponse))
                        val tokenManager = TokenManager
                        tokenManager.saveToken(loginResponse.token)
                    } else {
                        _loginStatus.value = (LoginStatus.Error("Empty response body"))
                    }
                } else {
                    _loginStatus.value = (LoginStatus.Error("Error: ${response.code()} ${response.message()}"))
                }
            } catch (e: Exception) {
                _loginStatus.value = (LoginStatus.Error("Exception: ${e.localizedMessage}"))
            }
        }

    }
    fun resetLoginStatus(){
        _loginStatus.value = null
    }


}