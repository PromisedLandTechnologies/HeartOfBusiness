import androidx.compose.runtime.*
import org.jetbrains.compose.web.renderComposable
import views.Page

fun main() {
	renderComposable(rootElementId = "root") {
		val data = remember { Receiver() }
		val page: Page = remember { Page(data) }
		page.currentView?.content()
	}
}