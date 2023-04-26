package views

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.dom.Text

class Error(private val message: String): View {
    @Composable
    override fun content() {
        Text(message)
    }
}