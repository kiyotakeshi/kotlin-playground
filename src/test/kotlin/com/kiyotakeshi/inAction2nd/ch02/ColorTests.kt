package com.kiyotakeshi.inAction2nd.ch02

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ColorTests {
    @Test
    fun print() {
        Color.BLUE.printColor()
        Color.RED.printColor()
        Color.GREEN.printColor()
    }

    @Test
    fun mnemonic() {
        assertThat(getMnemonic(Color.RED)).isEqualTo("Richard")
        assertThat(getMnemonic(Color.GREEN)).isEqualTo("Gave")
        assertThat(getMnemonic(Color.BLUE)).isEqualTo("Battle")
    }
}
