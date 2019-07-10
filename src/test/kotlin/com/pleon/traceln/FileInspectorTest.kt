package com.pleon.traceln

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.io.File

class FileInspectorTest {

    private lateinit var fileInspector: FileInspector

    @BeforeEach
    fun setUp() {
        fileInspector = FileInspector()
    }

    @ParameterizedTest
    @ValueSource(strings = ["/code.txt", "/code-large.txt"])
    fun detectCodeFile(filePath: String) {
        val file = File(javaClass.getResource(filePath).toURI())

        assertThat(fileInspector.isTextFile(file)).isEqualTo(true)
    }

    @ParameterizedTest
    @ValueSource(strings = ["/text-english.txt", "/text-farsi.txt"])
    fun detectRegularTextFile(filePath: String) {
        val file = File(javaClass.getResource(filePath).toURI())

        assertThat(fileInspector.isTextFile(file)).isEqualTo(true)
    }

    @Test
    fun doNotDetectImageFile() {
        val file = File(javaClass.getResource("/star.png").toURI())

        assertThat(fileInspector.isTextFile(file)).isEqualTo(false)
    }
}
