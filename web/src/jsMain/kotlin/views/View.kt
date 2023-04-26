package views

import androidx.compose.runtime.Composable

interface View {
    @Composable
    fun content(): Unit
}