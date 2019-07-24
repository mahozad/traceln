package com.pleon.traceln

import java.nio.file.Path

// For list of official github languages and colors see:
// [https://github.com/github/linguist/blob/master/lib/linguist/languages.yml]

fun main(args: Array<String>) {
    val traverser = Traverser(Path.of(args[0]))
    val inspector = Inspector()
    val adder = Adder()

    while (traverser.hasNext()) {
        val file = traverser.next()
        if (inspector.isTextFile(file)) {
            adder.add(inspector.getType(file), inspector.countLines(file))
        }
    }

    print(adder.getResult())
}
