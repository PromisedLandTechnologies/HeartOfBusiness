package views

import androidx.compose.runtime.Composable
import jsmodels.MemberDetailViewModel
import org.jetbrains.compose.web.css.fontFamily
import org.jetbrains.compose.web.css.textAlign
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

class MemberDetail(private val model: MemberDetailViewModel) : View {

    @Composable
    override fun content() {
        Span( {
            style {
                textAlign("center")
                fontFamily("sans-serif")
            }

        }) {
            Div { Text(model.member.name) }
            Div { Text(model.member.displayPhoneNumber) }
            Div { Text(model.member.email) }
            Div { Text(model.member.businessName) }
            Div { Text(model.member.businessDescription) }
            Div {
                Button(
                    attrs = { onClick { model.backRedirect() } }
                ) {
                    Text("Back")
                }
                Button(
                    attrs = { onClick { model.editRedirect() } }
                ) {
                    Text("Edit")
                }
                Button(
                    attrs = { onClick { model.delete() } }
                ) {
                    Text("Delete")
                }
            }
        }
    }
}
