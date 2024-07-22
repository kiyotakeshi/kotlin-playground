package com.kiyotakeshi.inAction2nd.ch02

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RectangleTests {
    @Test
    fun customAccessor() {
        val rectangle = Rectangle(41, 43)
        assertThat(rectangle.isSquare).isFalse()
    }
}