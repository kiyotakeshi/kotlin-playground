package com.kiyotakeshi.inAction2nd.ch01

import org.junit.jupiter.api.Test

class PersonTests {
    @Test
    fun main() {
        val persons = listOf(
            Person("Alice", age = 29),
            Person("Bob"),
        )

        // ?: 0 is used to handle null value
        // ?: is called Elvis operator
        val oldest = persons.maxByOrNull { it.age ?: 0 }
        println("the oldest is: $oldest")
    }
}