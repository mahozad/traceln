package com.pleon.traceln.component

import com.pleon.traceln.model.Entry
import java.nio.file.Path

class Tracer {

    fun trace(root: Path): List<Entry> {
        val traverser = Traverser(root)
        val inspector = Inspector()
        val adder = Adder()

        for (file in traverser) {
            val result = inspector.inspect(file)
            if (result.isTextFile) adder.add(result.type, result.lineCount)
        }

        return adder.getResult()
    }
}
