package com.pleon.traceln

class Adder {

    private val lineCount = mutableMapOf<String, Int>()

    fun getResult(): Map<String, Int> = lineCount

    fun add(type: String, lines: Int) {
        lineCount.merge(type, lines) { count, addition -> count + addition }
    }
}
