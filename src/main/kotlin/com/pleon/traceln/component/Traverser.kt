package com.pleon.traceln.component

import java.io.File
import java.nio.file.Files
import java.nio.file.Path

class Traverser(root: Path) : Iterator<File> {

    private var iterator = Files.walk(root).filter { it.toFile().isFile }.iterator()

    override fun hasNext() = iterator.hasNext()

    override fun next(): File = iterator.next().toFile()
}
