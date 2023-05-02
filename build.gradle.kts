import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.8.20"
	kotlin("plugin.spring") version "1.8.20"
	kotlin("plugin.jpa") version "1.8.20"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.github.f4b6a3:ulid-creator:5.2.0")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("com.mysql:mysql-connector-j")

	testImplementation("io.mockk:mockk:1.13.4")
	testImplementation("io.kotest:kotest-runner-junit5-jvm:5.5.5")
	testImplementation("io.kotest:kotest-assertions-core-jvm:5.5.5")
	testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
