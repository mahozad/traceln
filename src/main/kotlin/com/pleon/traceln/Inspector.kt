package com.pleon.traceln

import net.sf.jmimemagic.Magic.getMagicMatch
import java.io.File

class Inspector {

    fun isTextFile(file: File) = getMagicMatch(file, true).mimeType.contains("text")

    fun countLines(file: File) = file.readLines().size
}
