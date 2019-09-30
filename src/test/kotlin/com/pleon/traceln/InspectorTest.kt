package com.pleon.traceln

import com.pleon.traceln.component.Inspector
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.io.File

class InspectorTest {

    private lateinit var inspector: Inspector

    @BeforeEach fun setUp() {
        inspector = Inspector()
    }

    @ParameterizedTest
    @ValueSource(strings = ["/code.java", "/code-kotlin.kt", "/code-large.kt"])
    fun detectCodeFile(filePath: String) {
        val file = File(javaClass.getResource(filePath).toURI())

        val inspectionResult = inspector.inspect(file)

        assertThat(inspectionResult.isTextFile).isEqualTo(true)
    }

    @ParameterizedTest
    @ValueSource(strings = ["/code.txt", "/code-kotlin.txt", "/code-large.txt"])
    fun detectCodeFileWithTxtExtension(filePath: String) {
        val file = File(javaClass.getResource(filePath).toURI())

        val inspectionResult = inspector.inspect(file)

        assertThat(inspectionResult.isTextFile).isEqualTo(true)
    }

    @ParameterizedTest
    @ValueSource(strings = ["/text-english.txt", "/text-farsi.txt"])
    fun detectRegularTextFile(filePath: String) {
        val file = File(javaClass.getResource(filePath).toURI())

        val inspectionResult = inspector.inspect(file)

        assertThat(inspectionResult.isTextFile).isEqualTo(true)
    }

    @Test fun doNotDetectImageFile() {
        val file = File(javaClass.getResource("/star.png").toURI())

        val inspectionResult = inspector.inspect(file)

        assertThat(inspectionResult.isTextFile).isEqualTo(false)
    }

    @Test fun getJavaFileType() {
        val file = File(javaClass.getResource("/code.java").toURI())

        val inspectionResult = inspector.inspect(file)

        assertThat(inspectionResult.type).isEqualTo("Java")
    }

    @Test fun getKotlinFileType() {
        val file = File(javaClass.getResource("/code-kotlin.kt").toURI())

        val inspectionResult = inspector.inspect(file)

        assertThat(inspectionResult.type).isEqualTo("Kotlin")
    }

    @Test fun getTextFileType() {
        val file = File(javaClass.getResource("/code.txt").toURI())

        val inspectionResult = inspector.inspect(file)

        assertThat(inspectionResult.type).isEqualTo("Text")
    }

    @Test fun countOneJavaFileLines() {
        val file = File(javaClass.getResource("/code.txt").toURI())

        val inspectionResult = inspector.inspect(file)

        assertThat(inspectionResult.lineCount).isEqualTo(6)
    }

    @Test fun countLargeFileLines() {
        val file = File(javaClass.getResource("/code-large.txt").toURI())

        val inspectionResult = inspector.inspect(file)

        assertThat(inspectionResult.lineCount).isEqualTo(120)
    }
}
