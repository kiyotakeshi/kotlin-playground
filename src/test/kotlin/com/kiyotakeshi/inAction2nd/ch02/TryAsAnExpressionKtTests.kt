package com.kiyotakeshi.inAction2nd.ch02

import org.junit.jupiter.api.Test
import java.io.BufferedReader
import java.io.StringReader

class TryAsAnExpressionKtTests {
    @Test
    fun tryAsAnExpression() {
        val reader = BufferedReader(StringReader("not number"))
        readNumber(reader)
    }
}