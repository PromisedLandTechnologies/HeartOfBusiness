plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.6.10"
}

kotlin {
    jvm("desktop")

    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.0")
            }
        }
    }
}
