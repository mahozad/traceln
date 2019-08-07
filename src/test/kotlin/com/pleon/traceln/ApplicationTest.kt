package com.pleon.traceln

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ApplicationTest {

    private val outContent = ByteArrayOutputStream()
    private val originalOut = System.out

    @BeforeEach
    fun setUp() = System.setOut(PrintStream(outContent))

    @Test
    fun countAllFileLinesInPath() {
        val root = javaClass.getResource("/directory").path.replaceFirst("/", "")
        val args = arrayOf(root)

        main(args)

        assertThat(outContent.toString()).contains("15")
    }

    @Test
    fun runWithNoArgs() {
        val args = emptyArray<String>()

        val executable = { main(args) }

        assertThrows<Exception>(executable)
    }

    @Test
    fun runWithMoreThanOneArg() {
        val args = arrayOf("1", "2")

        val executable = { main(args) }

        assertThrows<Exception>(executable)
    }

    @AfterEach
    fun tearDown() = System.setOut(originalOut)
}
