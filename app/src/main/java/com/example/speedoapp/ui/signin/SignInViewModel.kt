package com.example.speedoapp.ui.signin

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedoapp.api.RetrofitFactory
import com.example.speedoapp.model.LoginRequest
import com.example.speedoapp.model.LoginResponse
import com.example.speedoapp.model.LoginStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class SignInViewModel() : ViewModel() {

    private val _loginStatus = MutableLiveData<LoginStatus>()
    val loginStatus: LiveData<LoginStatus> get() = _loginStatus

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = LoginRequest(email, password)
                val response = RetrofitFactory.apiService.login(request)

                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        _loginStatus.postValue(LoginStatus.Success(loginResponse))
                    } else {
                        _loginStatus.postValue(LoginStatus.Error("Empty response body"))
                    }
                } else {
                    _loginStatus.postValue(LoginStatus.Error("Error: ${response.code()} ${response.message()}"))
                }
            } catch (e: Exception) {
                _loginStatus.postValue(LoginStatus.Error("Exception: ${e.localizedMessage}"))
            }
        }

    }
}
