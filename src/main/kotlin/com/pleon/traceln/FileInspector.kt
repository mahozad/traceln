package com.pleon.traceln

import java.io.File
import java.nio.file.Files

class FileInspector {

    fun isTextFile(file: File) = Files.probeContentType(file.toPath()).contains("text")
}
