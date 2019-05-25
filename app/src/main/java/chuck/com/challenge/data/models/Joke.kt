package chuck.com.challenge.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Joke(val id: Int, @SerializedName("joke") val value: String, val categories: List<String>) : Parcelable
