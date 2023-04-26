package com.inwhob.plugins

import com.inwhob.commonmodels.Member
import com.inwhob.dao.dao
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.util.*

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondRedirect("/app/index.html")
        }
        route("/api") {

            get("/all") {
                call.respond(dao.allMembers())
            }
            post {
                val member = call.receive<Member>()
                dao.addNewMember(
                    firstName = member.firstName,
                    lastName = member.lastName,
                    email = member.email,
                    phoneNumber = member.phoneNumber,
                    businessName = member.businessName,
                    businessDescription = member.businessDescription
                )?.let {
                    call.respond(it)
                }
            }
            route("{id}") {
                get {
                    val id = call.parameters.getOrFail<Int>("id").toInt()
                    dao.member(id)?.let { call.respond(it) }
                }
                post {
                    val id = call.parameters.getOrFail<Int>("id").toInt()
                    val member = call.receive<Member>()
                    dao.editMember(id, member)
                }
                delete {
                    val id = call.parameters.getOrFail<Int>("id").toInt()
                    dao.deleteMember(id)
                }
            }
        }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
        static("/app") {
            resources("app")
        }
    }
}
