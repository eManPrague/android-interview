package cz.eman.android.interview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.eman.android.interview.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel(
    private val repository: Repository
): ViewModel() {

    fun onClick(
        jmeno: String,
        prijmeni: String
    ): Boolean {
        viewModelScope.launch {
            runBlocking(Dispatchers.IO) {
                repository.saveName(jmeno, prijmeni)
            }
            return@launch
        }
        return false
    }
}