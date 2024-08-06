package com.kiyotakeshi.playground

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ExampleKtTests {
    @Test
    fun ifIsExpression() {
        val num = 2
        val result = if (num % 2 == 1) "Odd" else "Even"
        assertThat(result).isEqualTo("Even")
    }

    @Test
    fun whenIsExpression() {
        val name = "jiro"
        val age = 0

        val result = when {
            name == "taro" -> "name is taro"
            age >= 20 -> "over 20"
            else -> "other"
        }
        assertThat(result).isEqualTo("other")
    }

    @Test
    fun elvisOperator() {
        val result: String? = null
        assertThat(result ?: "result is null").isEqualTo("result is null")
    }

    @Test
    fun lateinitName() {
        val user = User()
        assertThrows<UninitializedPropertyAccessException> {
            user.isValidName
        }
    }

    @Test
    fun customProperty() {
        val user = User()
        user.name = "taro"

        assertThat(user.isValidName).isTrue

        user.name = ""
        assertThat(user.isValidName).isFalse
    }

    @Test
    fun dataClassEquals() {
        val person1 = Human(1, "taro")
        val person2 = Human(1, "taro")
        val person3 = Human(2, "taro")

        assertThat(person1).isEqualTo(person2)
        assertThat(person1).isNotEqualTo(person3)
    }

    @Test
    fun dataClassComponentN() {
        val person = Human(1, "taro")

        // 順番を指定して property にアクセスできる
        assertThat(person.component1()).isEqualTo(1)
        assertThat(person.component2()).isEqualTo("taro")
    }
}