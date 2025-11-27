plugins {
    application
    kotlin("jvm") version "2.2.21"
    id("io.ktor.plugin") version "2.3.11"
}

group = "uk.ac.leeds.comp2850"
version = "1.1-SNAPSHOT"

repositories {
    mavenCentral()
}

val ktorVersion = "2.3.11"
val logbackVersion = "1.4.14"
val pebbleVersion = "3.2.2"

dependencies {
    // Ktor server core
    implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-sessions-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-status-pages-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-call-logging-jvm:$ktorVersion")

    // Pebble templating
    implementation("io.pebbletemplates:pebble:$pebbleVersion")

    // Logging
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    // CSV handling
    implementation("org.apache.commons:commons-csv:1.10.0")

    // Testing
    testImplementation(kotlin("test"))
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktorVersion")
    testImplementation("io.ktor:ktor-client-content-negotiation-jvm:$ktorVersion")
<<<<<<< HEAD
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:2.2.21")
=======
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:2.2.21") {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-test-junit")
    }
>>>>>>> minimal/main
}

application {
    mainClass.set("MainKt")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaExec> {
    // Enable development mode for hot reload
    systemProperty("io.ktor.development", "true")
}

kotlin {
    jvmToolchain(21)
}
<<<<<<< HEAD

// Code quality: Detekt (static analysis)
// Reports violations as warnings, doesn't fail build
detekt {
    config.setFrom(files("detekt.yml"))
    buildUponDefaultConfig = true
    ignoreFailures = true // Report but don't fail build
}

// Code quality: ktlint (code style)
// Reports violations as warnings, doesn't fail build
configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    ignoreFailures.set(true) // Report but don't fail build
}
=======
>>>>>>> minimal/main
