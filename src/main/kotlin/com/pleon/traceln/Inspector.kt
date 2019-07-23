package com.pleon.traceln

import net.sf.jmimemagic.Magic.getMagicMatch
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.streams.toList

private val typesPath = Path.of(Adder::class.java.getResource("/types.txt").toURI())

class Inspector {

    private val language = Files.lines(typesPath).toList()
        .associateBy({ it.substringBefore(':') }, { it.substringAfter(':') })

    fun isTextFile(file: File) = getMagicMatch(file, true).mimeType.contains("text")

    fun countLines(file: File) = file.readLines().size

    fun getType(file: File) = language.getOrDefault(file.name.substringAfter('.'), "Other")
}
