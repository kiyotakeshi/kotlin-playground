package com.kiyotakeshi.inAction2nd.ch02

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.BufferedReader
import java.io.StringReader

class TryAsAnExpressionKtTests {
    @Test
    fun tryAsAnExpression() {
        val reader = BufferedReader(StringReader("not number"))
        assertThat(readNumber(reader)).isNull()

        val reader2 = BufferedReader(StringReader("42"))
        assertThat(readNumber(reader2)).isEqualTo(42)
    }
}
