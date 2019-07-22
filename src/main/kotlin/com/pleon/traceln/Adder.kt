package com.pleon.traceln

class Adder {

    private val language = mapOf(
        Pair("java", "Java"),
        Pair("kt", "Kotlin")
    )

    private val lineCount = mutableMapOf(
        Pair("Java", 0),
        Pair("Kotlin", 0),
        Pair("Other", 0)
    )

    fun getMap(): Map<String, Int> = lineCount

    fun add(extension: String, lines: Int) {
        val language = language.getOrDefault(extension, "Other")
        lineCount[language] = lineCount[language]!! + lines
    }
}
