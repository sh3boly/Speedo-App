import androidx.navigation.NavController
import com.example.speedoapp.navigation.AppRoutes.SIGNIN_ROUTE
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenManager: PreferencesManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { tokenManager.getToken() } // Get the first emitted value
        val request = if (token != null) {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            chain.request()
        }
        return chain.proceed(request)
    }
    private fun handleTokenExpiration(chain: Interceptor.Chain): Response {
        tokenManager.removeToken()
        return chain.proceed(chain.request())
    }
}