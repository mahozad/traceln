package com.pleon.traceln

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
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

        main(arrayOf(root))

        assertThat(outContent.toString()).contains("15")
    }

    @AfterEach
    fun tearDown() = System.setOut(originalOut)
}
