package com.kiyotakeshi.inAction2nd.ch03

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ExtensionsKtTests {
    @Test
    fun lastChar() {
        assertThat("Kotlin".lastChar()).isEqualTo('n')
    }

    @Test
    fun customProperty() {
        assertThat("Kotlin".lastChar).isEqualTo('n')

        val sb = StringBuilder("Kotlin?")
        assertThat(sb.lastChar).isEqualTo('?')

        sb.lastChar = '!'
        assertThat(sb.toString()).isEqualTo("Kotlin!")
    }
}
