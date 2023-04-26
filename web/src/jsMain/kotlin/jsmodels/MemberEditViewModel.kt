package jsmodels

import com.inwhob.commonmodels.Member

class MemberEditViewModel(member: Member, val backRedirect: () -> Unit, val editOk: (Member) -> Unit) {
    private val id = member.id
    val firstName = InputViewModel(member.firstName)
    val lastName = InputViewModel(member.lastName)
    val phoneNumber = InputViewModel(member.phoneNumber)
    val email = InputViewModel(member.email)
    val businessName = InputViewModel(member.businessName)
    val businessDescription = InputViewModel(member.businessDescription)

    fun updatedMember(): Member {
        return Member(
            id = id,
            firstName = firstName.value,
            lastName = lastName.value,
            email = email.value,
            phoneNumber = phoneNumber.value,
            businessName = businessName.value,
            businessDescription = businessDescription.value
        )
    }
}
