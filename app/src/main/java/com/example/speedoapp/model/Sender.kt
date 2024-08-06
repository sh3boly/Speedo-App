import com.example.speedoapp.constants.Constants
import com.google.gson.annotations.SerializedName

data class Sender(
    @SerializedName(Constants.SENDER_ACCOUNT)
    val account: String,
    @SerializedName(Constants.SENDER_NAME)
    val name: String
)