package com.kiyotakeshi.inAction2nd.ch03

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class LocalFunctionsAndExtensionsKtTests {
    @Test
    fun validateSuccess() {
        val user = User(1, "mike", "los angeles")
        saveUser(user)
    }

    @Test
    fun validateFailure() {
        val user = User(1, "", "los angeles")
        assertThrows(IllegalArgumentException::class.java) {
            saveUser(user)
        }.message.let {
            assertEquals("can't save user 1: empty name", it)
        }
    }
}
