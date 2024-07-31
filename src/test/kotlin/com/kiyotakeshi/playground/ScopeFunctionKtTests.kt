package com.kiyotakeshi.playground

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScopeFunctionKtTests {

    @Test
    fun scopeFunction() {
        val numbers = mutableListOf(1, 2, 3, 4)
        val result = numbers
            .map { it * 2 }
            .filter { it > 5 }
            .let {
                println(it) // [6, 8]
                it.sum()
            }
        assertThat(result).isEqualTo(14)
    }

    @Test
    fun scopeFunction2() {
        var name: String? = null
        val result: String? = name?.let {
            // this block will not be executed
            println(it)
            it.uppercase()
        }
        assertThat(result).isNull()

        name = "mike"
        val result1: String? = name?.let {
            println(it)
            it.uppercase()
        }
        assertThat(result1).isEqualTo("MIKE")
    }
}