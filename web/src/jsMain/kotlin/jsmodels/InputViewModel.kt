package jsmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class InputViewModel(initialValue: String) {
    var value by mutableStateOf(initialValue)
}