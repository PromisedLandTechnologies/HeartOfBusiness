package views

import androidx.compose.runtime.Composable
import jsmodels.MemberListViewModel
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

class MemberList(private val model: MemberListViewModel) : View {
    @Composable
    override fun content() {
        Div({
            style {
                padding(5.px)
                textAlign("center")
                fontFamily("sans-serif")
            }
        }) {
            Button(
                attrs = { onClick { model.newMemberRedirect() }
                }
            ) { Text("Add") }
            model.members.forEach {
                Div{ memberSummaryView(it) }
            }
        }
    }
}