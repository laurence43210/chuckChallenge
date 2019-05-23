package chuck.com.challenge.data.models

data class ResponseWrapper<T>(val type: String, val value: List<T>)