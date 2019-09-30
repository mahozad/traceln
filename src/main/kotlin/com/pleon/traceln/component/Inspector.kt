package com.pleon.traceln.component

import net.sf.jmimemagic.Magic.getMagicMatch
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.streams.toList

class Inspector {

    inner class InspectionResult(val isTextFile: Boolean, val type: String, val lineCount: Int)

    private val type = Files.lines(Path.of(javaClass.getResource("/types.txt").toURI()))
        .toList().associateBy({ it.substringBefore(':') }, { it.substringAfter(':') })

    fun inspect(file: File) = InspectionResult(isTextFile(file), type(file), lineCount(file))

    private fun isTextFile(file: File) = getMagicMatch(file, true).mimeType.contains("text")

    private fun type(file: File) = type.getOrDefault(file.name.substringAfter('.'), "Other")

    private fun lineCount(file: File) = file.readLines().size
}
