package com.kiyotakeshi.inAction2nd.ch02

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RectangleKtTests {

    @Test
    fun square() {
        val unitSquare = createUnitSquare()
        assertThat(unitSquare.isSquare).isTrue()
    }
}