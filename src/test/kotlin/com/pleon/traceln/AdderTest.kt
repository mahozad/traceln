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
    fun checkMapSizeForOneEntry() {
        adder.add("Java", 10)

        assertThat(adder.getResult().size).isEqualTo(1)
    }

    @Test
    fun getResultForOneJavaFile() {
        adder.add("Java", 10)

        assertThat(adder.getResult()[0].lines).isEqualTo(10)
    }

    @Test
    fun getResultForTwoJavaFile() {
        adder.add("Java", 10)
        adder.add("Java", 6)

        assertThat(adder.getResult()[0].lines).isEqualTo(16)
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

        assertThat(adder.getResult()[0].lines).isEqualTo(10)
        assertThat(adder.getResult()[1].lines).isEqualTo(15)
    }

    @Test
    fun getPercentForOneJava() {
        adder.add("Java", 4)

        assertThat(adder.getResult()[0].percentage).isEqualTo(100.0)
    }

    @Test
    fun getPercentForEqualJavaAndKotlin() {
        adder.add("Java", 13)
        adder.add("Kotlin", 13)

        assertThat(adder.getResult()[0].percentage).isEqualTo(50.0)
    }
}
