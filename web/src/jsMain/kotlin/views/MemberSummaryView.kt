package views

import androidx.compose.runtime.Composable
import jsmodels.MemberSummaryViewModel
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*

@Composable
fun memberSummaryView(model: MemberSummaryViewModel) {
    Span({
        style {
            textAlign("center")
            fontFamily("sans-serif")
        }

    }) {
        Div {
            Span(attrs = {onClick { model.onClick(model.member.id) }}) {
                Text(model.member.name)
            }
                Text(": ${model.member.businessName}")
        }
    }
}