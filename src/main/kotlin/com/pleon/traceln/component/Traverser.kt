package com.pleon.traceln.component

import java.io.File
import java.nio.file.Files
import java.nio.file.Path

class Traverser(root: Path) : Iterator<File> {

    private var iterator = Files.walk(root)
        .map(Path::toFile)
        .filter(File::isFile)
        .iterator()

    override fun hasNext() = iterator.hasNext()

    override fun next(): File = iterator.next()
}
