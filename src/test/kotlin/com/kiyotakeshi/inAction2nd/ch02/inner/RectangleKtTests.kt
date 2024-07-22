package com.kiyotakeshi.inAction2nd.ch02.inner

// class, function を区別せず import キーワードを使用する
import com.kiyotakeshi.inAction2nd.ch02.Rectangle
import com.kiyotakeshi.inAction2nd.ch02.createUnitSquare

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RectangleKtTests {

    @Test
    fun square() {
        val unitSquare: Rectangle = createUnitSquare()
        assertThat(unitSquare.isSquare).isTrue()
    }
}