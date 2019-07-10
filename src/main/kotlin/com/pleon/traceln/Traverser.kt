package com.pleon.traceln

import java.io.File
import java.nio.file.Files
import java.nio.file.Files.isDirectory
import java.nio.file.Path

class Traverser(root: Path) : Iterator<File> {

    private var iterator = Files.walk(root).iterator()

    override fun hasNext() = iterator.hasNext()

    override fun next(): File {
        var next = iterator.next()
        while (isDirectory(next)) next = iterator.next()
        return next.toFile()
    }
}
