import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.30"
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.8"
    id("jacoco") // for generating code coverage for codecov.io
}

group = "com.pleon"
version = "0.1"

sourceSets {
    getByName("main").java.srcDirs("src/main/kotlin")
    getByName("test").java.srcDirs("src/test/kotlin")
}

repositories {
    jcenter()
    mavenCentral()
}

application {
    mainClassName = "com.pleon.traceln.Main"
}

javafx {
    version = "13"
    modules(
        "javafx.base", "javafx.controls", "javafx.fxml",
        "javafx.graphics", "javafx.media", "javafx.web", "javafx.swing"
    )
}

tasks.withType<Wrapper> {
    // Add a gradle wrapper script to your source folders (by running the wrapper task).
    // The wrapper script when invoked, downloads the defined gradle version, and executes it.
    // By distributing the wrapper with your project, anyone can work with it without needing to install Gradle beforehand
    gradleVersion = "6.6"
}

tasks.withType<Jar> {
    // Define main class in the manifest of output jar file when generating one
    manifest.attributes("Main-Class" to "com.pleon.traceln.MainKt")
}

tasks.withType<KotlinCompile> {
    // Target version of the generated JVM bytecode (1.6, 1.8, 9, 10, 11 or 12), default is 1.6
    kotlinOptions.jvmTarget = "12"
}

tasks.withType<Test> {
    // Even though Gradle 4.6 (and newer versions) has a native support for JUnit 5,
    // this support is not enabled by default. If we want to enable it,
    // we have to ensure that the test task uses JUnit 5 instead of JUnit 4.
    useJUnitPlatform()
}

// for generating code coverage for codecov.io
tasks.named("check") { dependsOn("jacocoTestReport") }
tasks.withType<JacocoReport> {
    reports {
        xml.isEnabled = true
        csv.isEnabled = true
        html.isEnabled = true
        // html.destination = File("${buildDir}/reports/jacoco/html")
    }
}

dependencies {
    implementation("net.sf.jmimemagic:jmimemagic:0.1.5")
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.5.0")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.5.0") // For gradle test task
    testImplementation("org.assertj:assertj-core:3.19.0")
    testImplementation("io.mockk:mockk:1.10.5")
}
