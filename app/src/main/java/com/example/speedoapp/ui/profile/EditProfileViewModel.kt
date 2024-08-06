//package com.example.speedoapp.ui.profile
//
//import AuthInterceptor
//import TokenManager
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.speedoapp.model.UpdateUserRequest
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//import com.example.speedoapp.model.UpdateUserResponse
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import com.example.speedoapp.api.UpdateUserApi
//import com.example.speedoapp.constants.Constants.BASE_URL
//import okhttp3.OkHttpClient
//
//data class UpdateUserUiState(
//    val isLoading: Boolean = false,
//    val error: String? = null,
//    val user: UpdateUserResponse? = null
//)
//
//class UpdateUserViewModel(private val tokenManager: TokenManager) : ViewModel() {
//
//    private val _uiState = MutableStateFlow<UpdateUserUiState>(UpdateUserUiState())
//    val uiState: StateFlow<UpdateUserUiState> = _uiState
//
//
//    private lateinit var updateUserService: UpdateUserApi
//
//    init {
//        updateUserService = createUserService()
//    }
//
//    private fun createUserService(): UpdateUserApi {
//        val okHttpClient = OkHttpClient.Builder()
//            .addInterceptor(AuthInterceptor(tokenManager))
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHttpClient)
//            .build()
//
//        return retrofit.create(UpdateUserApi::class.java)
//
//    }
//
//    fun updateUser(updateUserRequest: UpdateUserRequest) {
//        viewModelScope.launch {
//            _uiState.value = UpdateUserUiState(isLoading = true)
//            try {
//                val user = updateUserService.updateUser(updateUserRequest)
//                _uiState.value = UpdateUserUiState(user = user)
//            } catch (e: Exception) {
//                _uiState.value = UpdateUserUiState(error = e.message)
//            }
//        }
//    }
//}
//
//
