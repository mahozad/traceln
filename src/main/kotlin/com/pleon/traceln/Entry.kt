package com.pleon.traceln

data class Entry(val name: String, val lines: Int, val percentage: Double) {
    override fun toString() = "Type: $name\tLines: $lines\tPercentage: $percentage\n"
}
