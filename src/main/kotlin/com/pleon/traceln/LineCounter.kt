package com.pleon.traceln

import java.io.File

class LineCounter {

    fun countLines(file: File) = file.readLines().size
}
