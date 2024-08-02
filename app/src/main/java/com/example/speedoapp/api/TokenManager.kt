import android.content.Context
import android.content.SharedPreferences

object TokenManager {
    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    }

    fun saveToken(token: String) {
        val editor = prefs.edit()
        editor.putString("token", token)
        editor.apply()
    }

    fun getToken(): String? {
        return prefs.getString("token", null)
    }

    fun removeToken() {
        val editor = prefs.edit()
        editor.remove("token")
        editor.apply()
    }
}