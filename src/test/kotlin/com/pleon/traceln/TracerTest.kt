package com.pleon.traceln

import com.pleon.traceln.component.Tracer
import org.junit.jupiter.api.BeforeEach

class TracerTest {

    private lateinit var tracer: Tracer

    @BeforeEach fun setUp() {
        tracer = Tracer()
    }

//    @Test fun countAllFileLinesInPath() {
//        val root = Path.of(javaClass.getResource("/directory").path.replaceFirst("/", ""))
//
//        val result = tracer.trace(root)
//
//        assertThat(result.size).isEqualTo(1)
//    }
}
