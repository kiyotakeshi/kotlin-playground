package com.kiyotakeshi.inAction2nd.ch03

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class JoinToStringKtTests {
    @Test
    fun extensionFunction() {
        val list = listOf(1, 2, 3)
        assertThat(list.joinToString()).isEqualTo("1, 2, 3")

        val actual = list.joinToString(
            separator = "; ",
            prefix = "(",
            postfix = ")"
        )
        assertThat(actual).isEqualTo("(1; 2; 3)")
    }

    @Test
    fun extensionFunction2() {
        // receiver type mismatch:
        // e: file:///Users/takeshi-kiyota/gitdir/kotlin/kotlin-playground/src/test/kotlin/com/kiyotakeshi/inAction2nd/ch03/JoinToStringKtTests.kt:23:27 Unresolved reference. None of the following candidates is applicable because of receiver type mismatch:
        // val list = listOf(1, 2, 3)
        val list = listOf("a", "b", "c")
        val actual = list.join(
            separator = "; ",
            prefix = "(",
            suffix = ")"
        )
        assertThat(actual).isEqualTo("(a; b; c)")
    }
}
