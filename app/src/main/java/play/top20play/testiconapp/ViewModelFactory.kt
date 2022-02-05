package play.top20play.testiconapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import play.top20play.testiconapp.api.repo.MainRepository

class ViewModelFactory constructor(private val repository: MainRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("VM Not Found")
        }
    }
}