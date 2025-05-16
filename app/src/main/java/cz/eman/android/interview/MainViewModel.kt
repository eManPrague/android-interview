package cz.eman.android.interview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.eman.android.interview.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

data class MainState(
    val number: Int = 0,
    val names: MutableList<String> = mutableListOf()
)

class MainViewModel(
    private val repository: Repository
): ViewModel() {
    val state = MutableStateFlow(MainState())

    fun onClick() {
        viewModelScope.launch {
            state.value = state.value.copy(number = state.value.number + 1)
            state.value.names.apply { add("Name ${repository.getRandomName(state.value.number)}") }
        }
    }
}