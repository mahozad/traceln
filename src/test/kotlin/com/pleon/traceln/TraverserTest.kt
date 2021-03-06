package com.pleon.traceln

import com.pleon.traceln.component.Traverser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Path

class TraverserTest {

    private val fileAsRoot = Path.of(javaClass.getResource("/directory/file.txt").toURI())
    private val dirAsRoot = Path.of(javaClass.getResource("/directory").toURI())

    @Test fun withDirectoryAsRootHasNextShouldReturnTrue() {
        val traverser = Traverser(dirAsRoot)

        val hasNext = traverser.hasNext()

        assertThat(hasNext).isEqualTo(true)
    }

//    @Test fun withDirectoryAsRootDoubleNextShouldReturnSecondFile() {
//        val traverser = Traverser(dirAsRoot)
//
//        traverser.next()
//        val next = traverser.next()
//
//        assertThat(next).isEqualTo(Path.of(javaClass.getResource("/directory/file2.txt").toURI()).toFile())
//    }

//    @Test fun withDirectoryAsRootThirdNextShouldReturnFileInSubdirectory() {
//        val traverser = Traverser(dirAsRoot)
//
//        traverser.next()
//        traverser.next()
//        val next = traverser.next()
//
//        assertThat(next).isEqualTo(Path.of(javaClass.getResource("/directory/sub-dir-1/file3.txt").toURI()).toFile())
//    }

//    @Test fun withDirectoryAsRootFourthNextShouldReturnSecondFileInSubdirectory() {
//        val traverser = Traverser(dirAsRoot)
//
//        traverser.next()
//        traverser.next()
//        traverser.next()
//        val next = traverser.next()
//
//        assertThat(next).isEqualTo(Path.of(javaClass.getResource("/directory/sub-dir-1/file4.txt").toURI()).toFile())
//    }

//    @Test fun withDirectoryAsRootFifthNextShouldReturnFileInInnerSubdirectory() {
//        val traverser = Traverser(dirAsRoot)
//
//        traverser.next()
//        traverser.next()
//        traverser.next()
//        traverser.next()
//        val next = traverser.next()
//
//        assertThat(next).isEqualTo(Path.of(javaClass.getResource("/directory/sub-dir-2/inner-sub-dir/file5.txt").toURI()).toFile())
//    }

//    @Test fun withDirectoryAsRootSixthNextShouldReturnImageFile() {
//        val traverser = Traverser(dirAsRoot)
//
//        traverser.next()
//        traverser.next()
//        traverser.next()
//        traverser.next()
//        traverser.next()
//        val next = traverser.next()
//
//        assertThat(next).isEqualTo(Path.of(javaClass.getResource("/directory/sub-dir-2/star.png").toURI()).toFile())
//    }

    @Test fun withFileAsRootHasNextShouldReturnTrue() {
        val traverser = Traverser(fileAsRoot)

        val hasNext = traverser.hasNext()

        assertThat(hasNext).isEqualTo(true)
    }

    @Test fun withFileAsRootHasNextAfterNextShouldReturnFalse() {
        val traverser = Traverser(fileAsRoot)

        traverser.next()
        val hasNext = traverser.hasNext()

        assertThat(hasNext).isEqualTo(false)
    }

    @Test fun withFileAsRootNextShouldReturnItself() {
        val traverser = Traverser(fileAsRoot)

        val next = traverser.next()

        assertThat(next).isEqualTo(fileAsRoot.toFile())
    }

    @Test fun emptyRootNextShouldThrowException() {
        // Empty folders are not copied to the build output folder, so we have to create it manually
        Files.createDirectory(Path.of("${System.getProperty("user.dir")}/empty"))
        val traverser = Traverser(Path.of("${System.getProperty("user.dir")}/empty"))

        val hasNext = traverser.hasNext()

        assertThat(hasNext).isEqualTo(false)
    }

    @Test fun withDirectoryAsRootNextShouldReturnFirstFile() {
        val traverser = Traverser(dirAsRoot)

        val next = traverser.next()

        assertThat(next).isEqualTo(fileAsRoot.toFile())
    }

    @AfterEach fun tearDown() {
        Files.deleteIfExists(Path.of("${System.getProperty("user.dir")}/empty"))
    }
}
