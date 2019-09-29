import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.8"
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

tasks.withType<KotlinCompile> {
    // Target version of the generated JVM bytecode (1.6, 1.8, 9, 10, 11 or 12), default is 1.6
    kotlinOptions.jvmTarget = "12"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("net.sf.jmimemagic:jmimemagic:0.1.5")
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.5.0")
    testImplementation("org.assertj:assertj-core:3.13.2")
    testImplementation("io.mockk:mockk:1.9.3")
}
