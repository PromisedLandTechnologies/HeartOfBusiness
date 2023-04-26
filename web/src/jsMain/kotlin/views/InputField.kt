package views

import androidx.compose.runtime.Composable
import jsmodels.InputViewModel
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.dom.Input

@Composable
fun inputField(input: InputViewModel) {
        Input(type = InputType.Text) {
            value(input.value)
            onInput { event -> input.value = event.value }
        }
}