package chuck.com.challenge.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import chuck.com.challenge.R

class BaseActivityViewModule : ViewModel() {

    private val selectedTabId by lazy {
        MutableLiveData<Int>().apply {
            value = R.id.randomJoke
        }
    }

    fun getSelectedTabId(): LiveData<Int> = selectedTabId

    fun setToRandomJoke() {
        selectedTabId.value = R.id.randomJoke
    }

    fun setToNameReplace() {
        selectedTabId.value = R.id.nameReplace
    }

    fun setToInfiniteList() {
        selectedTabId.value = R.id.infiniteList
    }
}