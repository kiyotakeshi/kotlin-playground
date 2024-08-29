package com.kiyotakeshi.playground

import org.junit.jupiter.api.Test

class BackingFieldKtTests {

    @Test
    fun `person2`() {
        val person2 = Person2()
        person2.name = "Alice"
        println(person2.name)
    }

    @Test
    fun `person3`() {
        val person3 = Person3()
        person3.name = "Bob"
        println(person3.name)
    }

    @Test
    fun `person4`() {
        val person4 = Person4()
        person4.name = "Charlie"
        println(person4.name)
    }
}
