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
        adder.add("Java", 10)

        assertThat(adder.getResult()["Java"]).isEqualTo(10)
    }

    @Test
    fun getResultForTwoJavaFile() {
        adder.add("Java", 10)
        adder.add("Java", 6)

        assertThat(adder.getResult()["Java"]).isEqualTo(16)
    }

    @Test
    fun checkMapSizeForOneEntry() {
        adder.add("Java", 10)

        assertThat(adder.getResult().size).isEqualTo(1)
    }

    @Test
    fun checkMapSizeForTwoDifferentEntry() {
        adder.add("Java", 10)
        adder.add("Kotlin", 9)

        assertThat(adder.getResult().size).isEqualTo(2)
    }

    @Test
    fun getResultForMultipleAdditions() {
        adder.add("Java", 10)
        adder.add("Other", 15)
        adder.add("Kotlin", 8)

        assertThat(adder.getResult()["Java"]).isEqualTo(10)
        assertThat(adder.getResult()["Other"]).isEqualTo(15)
    }
}
