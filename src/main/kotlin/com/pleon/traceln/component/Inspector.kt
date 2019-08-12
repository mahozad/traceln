package com.pleon.traceln.component

import net.sf.jmimemagic.Magic.getMagicMatch
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.streams.toList

class Inspector {

    private val type = Files.lines(Path.of(javaClass.getResource("/types.txt").toURI()))
        .toList().associateBy({ it.substringBefore(':') }, { it.substringAfter(':') })

    fun isTextFile(file: File) = getMagicMatch(file, true).mimeType.contains("text")

    fun countLines(file: File) = file.readLines().size

    fun getType(file: File) = type.getOrDefault(file.name.substringAfter('.'), "Other")
}
