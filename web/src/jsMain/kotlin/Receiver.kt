import com.inwhob.commonmodels.Member
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.w3c.fetch.RequestInit
import kotlin.js.json

class Receiver {
    private val baseAddress = "/api"

    suspend fun fetchAllMembers(): List<Member> {
        val response = window.fetch("$baseAddress/all")
            .await()
            .text()
            .await()
        return Json.decodeFromString(response)
    }

    suspend fun fetchMember(id: Int): Member {
        val response = window.fetch("$baseAddress/$id")
            .await()
            .text()
            .await()
        return Json.decodeFromString(response)
    }

    suspend fun updateMember(member: Member){
        window.fetch(
            "$baseAddress/${member.id}",
            RequestInit(
                method="POST",
                body = Json.encodeToString(member),
                headers = json("Content-Type" to "application/json")
            )
        )
            .await()
    }

    suspend fun addMember(member: Member): Member {
        val response = window.fetch(
            baseAddress,
            RequestInit(
                method="POST",
                body = Json.encodeToString(member),
                headers = json("Content-Type" to "application/json")
            )
        )
            .await()
            .text()
            .await()
        return Json.decodeFromString(response)
    }

    suspend fun deleteMember(id: Int) {
        window.fetch(
            "$baseAddress/$id",
            RequestInit(
                method="DELETE",
                headers = json("Content-Type" to "application/json")
            )
        )
            .await()
    }
}