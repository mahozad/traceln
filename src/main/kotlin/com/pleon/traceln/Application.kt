package com.pleon.traceln

import java.nio.file.Path

fun main( args: Array<String>) {
    val traverser = Traverser(Path.of(args[0]))
    val inspector = Inspector()
    var totalLineCount = 0

    while (traverser.hasNext()) {
        val file = traverser.next()
        if (inspector.isTextFile(file))
            totalLineCount += inspector.countLines(file)
    }

    print(totalLineCount)
}
