package com.pleon.traceln

import java.nio.file.Path

fun main() {
    println("Hello!")
}

class Application {

    fun getTotalLineCount(root: Path): Int {
        val traverser = Traverser(root)
        val inspector = Inspector()
        var totalLineCount = 0

        while (traverser.hasNext()) {
            val file = traverser.next()
            if (inspector.isTextFile(file))
                totalLineCount += inspector.countLines(file)
        }

        return totalLineCount
    }
}
