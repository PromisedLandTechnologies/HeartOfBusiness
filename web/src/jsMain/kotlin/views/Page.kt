package views

import Receiver
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.inwhob.commonmodels.Member
import jsmodels.MemberDetailViewModel
import jsmodels.MemberEditViewModel
import jsmodels.MemberListViewModel
import jsmodels.MemberSummaryViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class Page(private val data: Receiver){

    var currentView: View? by mutableStateOf( null )
        private set

    init {
        viewAllMembers()
    }

    private fun viewAllMembers() {
        MainScope().launch {
            val members = data.fetchAllMembers()
            val model = MemberListViewModel(
                List(members.size) { MemberSummaryViewModel(members[it], ::viewMemberDetails) }
            ) {
                val emptyMember = Member(-1, "First Name", "Last Name", "Email", "Phone Number", "Business Name", "Business Description")
                val model = MemberEditViewModel(emptyMember, ::viewAllMembers) { it: Member ->
                    MainScope().launch {
                        val addedMember: Member = data.addMember(it)
                        viewMemberDetails(addedMember.id)
                    }
                }
                currentView = MemberEdit(model)
            }
            currentView = MemberList(model)
        }
    }

    private fun viewMemberDetails(memberID: Int) {
        MainScope().launch {
            currentView = MemberDetail(
                MemberDetailViewModel(
                    data.fetchMember(memberID),
                    ::viewAllMembers,
                    { viewEditPage(memberID) },
                ) { deleteMember(memberID) },
            )
        }
    }

    private fun viewEditPage(memberID: Int) {
        MainScope().launch {
            currentView = MemberEdit(
                MemberEditViewModel(
                    data.fetchMember(memberID),
                    { viewMemberDetails(memberID) }
                ) { it: Member ->
                    MainScope().launch {
                        data.updateMember(it)
                        viewMemberDetails(memberID)
                    }
                }
            )
        }
    }

    private fun deleteMember(memberID: Int) {
        MainScope().launch {
            data.deleteMember(memberID)
            viewAllMembers()
        }
    }
}