package com.pleon.traceln.component

import com.pleon.traceln.model.Entry

class Adder {

    private val lineCount = mutableMapOf<String, Int>()
    private var totalLines = 0.0

    fun getResult() = lineCount.map { Entry(it.key, it.value, it.value / totalLines * 100) }

    fun add(type: String, lines: Int) {
        lineCount.merge(type, lines, Math::addExact).also { totalLines += lines }
    }
}
