package com.pleon.traceln

import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith

/**
 * `KotlinTest` library provides annotations and asserts, but it doesn't provide a test runner or an
 * overall framework for test execution. Which is why you still need JUnit or something like it
 * (so we used kotlin-test-junit5 in gradle).
 *
 * `KotlinTest` provides things such as:
 * - DSL with several styles to write tests
 * - Matcher DSL for readable assertions
 * - Generation of test data (property based testing)
 * - Table-driven tests
 * - Configurable parallel execution of tests
 * - Grouping of tests and selectively running these tests
 * - Interceptors (before/after testcase, spec or suite)
 * - Auto closing resources
 *
 * The [MockK] mocking library allows you to create mocks more easily in Kotlin. Classes in Kotlin are
 * final by default, and unlike other mocking libraries, MockK allows you to mock out final classes.
 *
 * We extend the junit runner with `MockKExtension::class` so we do not have to initialize MockK
 * in the setup method like this: `MockKAnnotations.init(this)`
 */
@Tag("Main")
@ExtendWith(MockKExtension::class)
internal class BaseTest {

    class User(val name: String, val age: Int)

    @MockK lateinit var mockedUser: User

    /**
     * Spy allows mocking only a particular part of some **existing object**.
     */
    @SpyK var spiedUser = User("Hey", 30)

    @BeforeEach
    internal fun setUp() {
        println("Setup method called")
        every { mockedUser.name } returns "Mahdi"
        every { spiedUser.name } returns "Hello!"
    }

    @Disabled @Test
    fun testIgnored() {
        assertThat(100).isEqualTo(101 - 2 + 1)
    }

    @Test
    fun testMockAsField() {
        assertThat(mockedUser.name).isEqualTo("Mahdi")
    }

    /**
     * You can use `@MockK` and `@RelaxedMockK` on function parameters as well.
     */
    @Test
    fun testMockAsMethodParameter(@MockK user: User) {
        every { user.age } returns 20

        assertThat(user.age).isEqualTo(20)
    }

    /**
     * **Relaxed mock** is a mock that returns some simple value for all functions. This allows to
     * skip specifying behavior for each case, while still allow to stub things you need.
     */
    @Test
    fun testMockMethodCalled(@RelaxedMockK user: User) {
        println(user.age)

        verify { user.age }
        // verify { user.name wasNot Called }
    }

    /**
     * To revert back object mock, use `unmockkAll` or `unmockkObject` in the tearDown method.
     */
    @Test
    fun testMockedObject() {
        // Arrange
        val util = object {
            fun add() {}
        }
        mockkObject(util)

        // Act
        util.add()

        // Assert
        verify(exactly = 1) { util.add() }
    }

    /**
     * Spy allows mocking only a particular part of some object.
     */
    @Test
    fun testSpy() {
        assertThat(spiedUser.name).isEqualTo("Hello!")
    }

    /**
     * unmockkAll is to revert mocks of singleton objects.
     */
    @AfterEach
    internal fun tearDown() {
        println("Teardown method called")
        unmockkAll()
    }
}
