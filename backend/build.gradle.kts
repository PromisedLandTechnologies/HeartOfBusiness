plugins {
	kotlin("jvm")
	application
}


application {
	mainClass.set("com.inwhob.ApplicationKt")
}

dependencies {
	val ktorVersion = "2.0.0"
	implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-host-common-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")

	implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

	implementation("ch.qos.logback:logback-classic:1.2.3")

	val exposedVersion = "0.41.1"
	implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

	implementation("com.h2database:h2:2.1.214")

	implementation(project(":shared"))
}


tasks.named<ProcessResources>("processResources") {
	dependsOn(":web:assemble")
	from(File(rootProject.project("web").buildDir, "distributions/")) {
		into("app")
	}
}