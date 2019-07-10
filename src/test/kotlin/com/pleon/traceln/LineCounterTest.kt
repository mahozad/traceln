package com.pleon.traceln

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

private val FILE_PATH = "${System.getProperty("user.dir")}/testFile.txt"

class LineCounterTest {

    private lateinit var testFile: File
    private lateinit var fileReader: LineCounter

    @BeforeEach
    fun setUp() {
        testFile = File(FILE_PATH).apply { createNewFile() }
        fileReader = LineCounter()
    }

    @Test
    fun countOneTextFileLines() {
        testFile.writeText("Hello.\nHow are you?")

        val lineCount = fileReader.countLines(testFile)

        assertThat(lineCount).isEqualTo(2)
    }

    @Test
    fun countOneKotlinFileLines() {
        testFile.writeText("fun main() {\nprintln(\"Hello!\")\n}\n")

        val lineCount = fileReader.countLines(testFile)

        assertThat(lineCount).isEqualTo(3)
    }

    @AfterEach
    fun tearDown() {
        testFile.delete()
    }
}
