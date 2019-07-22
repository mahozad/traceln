package com.pleon.traceln

import java.nio.file.Files
import java.nio.file.Path
import kotlin.streams.toList

private val sourcePath = Path.of(Adder::class.java.getResource("/languages.txt").toURI())

class Adder {

    private val language = Files.lines(sourcePath).toList().associateBy(
        { it.substringBefore(':') }, { it.substringAfter(':') }
    )

    private val lineCount = mutableMapOf<String, Int>()

    fun getMap(): Map<String, Int> = lineCount

    fun add(extension: String, lines: Int) {
        val language = language.getOrDefault(extension, "Other")
        lineCount.merge(language, lines) { count, addition -> count + addition }
    }
}
