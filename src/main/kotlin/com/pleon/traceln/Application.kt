package com.pleon.traceln

import java.nio.file.Path

fun main(args: Array<String>) {
    val traverser = Traverser(Path.of(args[0]))
    val inspector = Inspector()
    val adder = Adder()

    while (traverser.hasNext()) {
        val file = traverser.next()
        if (inspector.isTextFile(file))
            adder.add(inspector.getType(file), inspector.countLines(file))
    }

    print(adder.getResult().toString())
}
