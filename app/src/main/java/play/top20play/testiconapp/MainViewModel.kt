package play.top20play.testiconapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import play.top20play.testiconapp.data.BaseData
import play.top20play.testiconapp.api.repo.MainRepoImpl

class MainViewModel constructor(private val mainRepository: MainRepoImpl) : ViewModel() {

    private val _errorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private val _pictureList: MutableLiveData<BaseData> = MutableLiveData()
    val pictureList: LiveData<BaseData>
        get() = _pictureList

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean>
        get() = _loading

    var job: Job? = null


    fun getAllPicture(tag: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.search(tag)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _pictureList.postValue(response.body())
                    _loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        _errorMessage.postValue(message)
        _loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}