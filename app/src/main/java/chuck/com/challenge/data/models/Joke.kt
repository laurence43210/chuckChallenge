package chuck.com.challenge.data.models

import com.google.gson.annotations.SerializedName

data class Joke(val id: Int, @SerializedName("joke") val value: String, val categories: List<String>)
