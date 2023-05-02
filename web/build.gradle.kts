plugins {
	kotlin("multiplatform")
	id("org.jetbrains.compose")
}

version = "1.0-SNAPSHOT"

kotlin {

	js(IR) {
		browser()
		binaries.executable()
	}

	sourceSets {
		val jsMain by getting {
			dependencies {
				implementation(kotlin("stdlib-js"))
				implementation(compose.web.core)
				implementation(compose.runtime)
				implementation(project(":shared"))
			}
		}
	}
}