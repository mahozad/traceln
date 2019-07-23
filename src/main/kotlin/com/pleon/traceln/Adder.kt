package com.pleon.traceln

class Adder {

    private var totalLines = 0.0
    private val lineCount = mutableMapOf<String, Int>()

    fun getResult() = lineCount.map { Entry(it.key, it.value, it.value / totalLines * 100) }

    fun add(type: String, lines: Int) {
        totalLines += lines
        lineCount.merge(type, lines, Math::addExact)
    }
}
