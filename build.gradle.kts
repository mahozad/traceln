import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.40"
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.5"
}

group = "com.pleon"
version = "0.1"

repositories {
    jcenter()
    mavenCentral()
}

javafx {
    version = "12"
    modules = listOf(
        "javafx.base", "javafx.controls", "javafx.fxml",
        "javafx.graphics", "javafx.media", "javafx.web"
        /*, "javafx.swing", "javafx.swt"*/
    )
}

application {
    mainClassName = "HelloFX"
}

/**
 * This block is to tell gradle to use its native junit platform. We can also specify some useful
 * things such as excluded tags, etc. here. Currently seems to be not needed.
 */
/*tasks.named<Test>("test") {
    useJUnitPlatform()
}*/

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("net.sf.jmimemagic:jmimemagic:0.1.5")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:1.3.40")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.5.0")
    testImplementation("org.assertj:assertj-core:3.12.2")
    testImplementation("io.mockk:mockk:1.9.3")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
