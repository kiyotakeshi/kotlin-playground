package com.kiyotakeshi.inAction2nd.ch04

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InterfaceKtTests {
    @Test
    fun `interface and implementation`() {
        val button = Button()
        assertThat(button.click()).isEqualTo("I was clicked(button)")
        // default implementation
        assertThat(button.showOff()).isEqualTo("I'm clickable!")
    }
}
