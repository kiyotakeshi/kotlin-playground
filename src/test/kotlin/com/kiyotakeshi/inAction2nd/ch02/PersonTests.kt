package com.kiyotakeshi.inAction2nd.ch02

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PersonTests {

    @Test
    fun varHasSetter() {
        val alice = Person("Alice", isStudent = true)
        assertThat(alice.name).isEqualTo("Alice")

        val bob = Person("Bob", isStudent = false)
        // var has setter
        bob.isStudent = true
        assertThat(bob.isStudent).isTrue()
    }

    @Test
    fun notImplementToString() {
        val bob = Person("Bob", isStudent = false)

        println(bob) // com.kiyotakeshi.inAction2nd.ch02.Person@1caa0244
    }

    @Test
    fun notImplementEquals() {
        val alice = Person("Alice", isStudent = true)
        val alice2 = Person("Alice", isStudent = true)

        assertThat(alice).isNotEqualTo(alice2)
    }
}