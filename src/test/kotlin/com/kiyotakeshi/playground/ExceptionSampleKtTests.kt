package com.kiyotakeshi.playground

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class ExceptionSampleKtTests {
    @Test
    fun exceptionSample() {
        assertThat(nameLength("mike")).isEqualTo(4)
        assertThat(nameLength(null)).isNull()
    }
}