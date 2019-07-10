package com.pleon.traceln

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Path

class ApplicationTest {

    @Test
    fun countAllFileLinesInPath() {
        val application = Application()
        val root = Path.of(javaClass.getResource("/directory").toURI())

        val totalLineCount = application.getTotalLineCount(root)

        assertThat(totalLineCount).isEqualTo(15)
    }

    @Test
    fun countFileLinesInArbitraryDirectory() {
        val application = Application()
        val root = Path.of("D:\\Project\\Buyt\\app\\src\\")

        val totalLineCount = application.getTotalLineCount(root)

        assertThat(totalLineCount).isGreaterThan(15_000)
    }
}
