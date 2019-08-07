package com.pleon.traceln

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.io.File

class InspectorTest {

    private lateinit var inspector: Inspector

    @BeforeEach
    fun setUp() {
        inspector = Inspector()
    }

    @ParameterizedTest
    @ValueSource(strings = ["/code.java", "/code-kotlin.kt", "/code-large.kt"])
    fun detectCodeFile(filePath: String) {
        val file = File(javaClass.getResource(filePath).toURI())

        assertThat(inspector.isTextFile(file)).isEqualTo(true)
    }

    @ParameterizedTest
    @ValueSource(strings = ["/code.txt", "/code-kotlin.txt", "/code-large.txt"])
    fun detectCodeFileWithTxtExtension(filePath: String) {
        val file = File(javaClass.getResource(filePath).toURI())

        assertThat(inspector.isTextFile(file)).isEqualTo(true)
    }

    @ParameterizedTest
    @ValueSource(strings = ["/text-english.txt", "/text-farsi.txt"])
    fun detectRegularTextFile(filePath: String) {
        val file = File(javaClass.getResource(filePath).toURI())

        assertThat(inspector.isTextFile(file)).isEqualTo(true)
    }

    @Test
    fun doNotDetectImageFile() {
        val file = File(javaClass.getResource("/star.png").toURI())

        assertThat(inspector.isTextFile(file)).isEqualTo(false)
    }

    @Test
    fun getJavaFileType() {
        val file = File(javaClass.getResource("/code.java").toURI())

        assertThat(inspector.getType(file)).isEqualTo("Java")
    }

    @Test
    fun getKotlinFileType() {
        val file = File(javaClass.getResource("/code-kotlin.kt").toURI())

        assertThat(inspector.getType(file)).isEqualTo("Kotlin")
    }

    @Test
    fun getTextFileType() {
        val file = File(javaClass.getResource("/code.txt").toURI())

        assertThat(inspector.getType(file)).isEqualTo("Text")
    }

    @Test
    fun countOneJavaFileLines() {
        val file = File(javaClass.getResource("/code.txt").toURI())

        val lineCount = inspector.countLines(file)

        assertThat(lineCount).isEqualTo(6)
    }

    @Test
    fun countLargeFileLines() {
        val file = File(javaClass.getResource("/code-large.txt").toURI())

        val lineCount = inspector.countLines(file)

        assertThat(lineCount).isEqualTo(120)
    }
}
