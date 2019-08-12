package com.pleon.traceln.component

import com.pleon.traceln.model.Entry
import java.nio.file.Path

class Tracer {

    fun trace(root: Path): List<Entry> {
        val traverser = Traverser(root)
        val inspector = Inspector()
        val adder = Adder()

        while (traverser.hasNext()) {
            val file = traverser.next()
            if (inspector.isTextFile(file)) {
                adder.add(inspector.getType(file), inspector.countLines(file))
            }
        }

        return adder.getResult()
    }
}
