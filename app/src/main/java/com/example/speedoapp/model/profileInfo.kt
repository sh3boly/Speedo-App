import com.example.speedoapp.constants.Constants
import com.google.gson.annotations.SerializedName

data class profileInfo(
    @SerializedName(Constants.NAME)
    val name: String,
    @SerializedName(Constants.EMAIL)
    val email: String,
    @SerializedName(Constants.BIRTH)
    val dateOfBirth: String,
    @SerializedName(Constants.PROFILE_COUNTRY)
    val country: String,
    @SerializedName(Constants.ACCOUNT)
    val account: String
)