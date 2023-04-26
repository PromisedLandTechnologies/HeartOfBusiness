package views

import androidx.compose.runtime.Composable
import jsmodels.MemberEditViewModel
import org.jetbrains.compose.web.css.fontFamily
import org.jetbrains.compose.web.css.textAlign
import org.jetbrains.compose.web.dom.*

class MemberEdit(private val model: MemberEditViewModel): View {
    @Composable
    override fun content() {
        Span({
            style {
                textAlign("center")
                fontFamily("sans-serif")
            }

        }) {
            Div {
                inputField(model.firstName)
            }
            Div {
                inputField(model.lastName)
            }
            Div {
                inputField(model.phoneNumber)
            }
            Div {
                inputField(model.email)
            }
            Div {
                inputField(model.businessName)
            }
            Div {
                inputField(model.businessDescription)
            }
            Div {
                Button(attrs = { onClick { model.backRedirect() } }) { Text("Back") }
                Button(attrs = { onClick { model.editOk(model.updatedMember()) } }) { Text("Ok") }
            }
        }
    }
}

