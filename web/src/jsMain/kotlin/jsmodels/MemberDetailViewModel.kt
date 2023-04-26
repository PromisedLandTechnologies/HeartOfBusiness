package jsmodels

import com.inwhob.commonmodels.Member

open class MemberDetailViewModel(
    val member: Member,
    val backRedirect: () -> Unit,
    val editRedirect: () -> Unit,
    val delete: () -> Unit
    )