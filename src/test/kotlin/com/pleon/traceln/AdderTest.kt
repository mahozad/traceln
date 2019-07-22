package com.pleon.traceln

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AdderTest {

    private lateinit var adder: Adder

    @BeforeEach
    fun setUp() {
        adder = Adder()
    }

    @Test
    fun getResultForOneJavaFile() {
        adder.add("java", 10)

        assertThat(adder.getMap()["Java"]).isEqualTo(10)
    }

    @Test
    fun getResultForOneKotlinFile() {
        adder.add("kt", 7)

        assertThat(adder.getMap()["Kotlin"]).isEqualTo(7)
    }

    @Test
    fun getResultForOneTxtFile() {
        adder.add("txt", 15)

        assertThat(adder.getMap()["Other"]).isEqualTo(15)
    }

    @Test
    fun getResultForMultipleAdditions() {
        adder.add("java", 10)
        adder.add("txt", 15)
        adder.add("kt", 8)

        assertThat(adder.getMap()["Java"]).isEqualTo(10)
        assertThat(adder.getMap()["Other"]).isEqualTo(15)
    }
}