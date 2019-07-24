package com.pleon.traceln

import java.nio.file.Path

// For list of official Github languages and their colors see [https://github.com/ozh/github-colors]
//     and [https://github.com/github/linguist/blob/master/lib/linguist/languages.yml]
// For how Github language detection works see [https://github.com/github/linguist]
// And for using Github REST api see [https://developer.github.com/v3/repos/]

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
