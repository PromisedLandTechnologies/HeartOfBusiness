package com.inwhob

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*

import com.inwhob.dao.DatabaseFactory
import com.inwhob.plugins.configureRouting

fun main() {
    embeddedServer(Netty, port = 80, host = "192.168.1.5", module = Application::dataServer).start(wait = true)
}

fun Application.dataServer() {
    DatabaseFactory.init()
    install(Routing)
    install(ContentNegotiation) {
        json()
    }
    configureRouting()
}