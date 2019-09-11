package chuck.com.challenge.data.wrappers

sealed class DataState<T> {

    data class Success<T>(val data: T) : DataState<T>()
    class Loading<T> : DataState<T>()
    data class Error<T>(val message: String) : DataState<T>()
}