package com.kiyotakeshi.playground

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NullableKtTests {

    @Test
    fun nullableNull() {
        var nullableString: String? = null

        assertThat("value is : ${nullableString?.length}").isEqualTo("value is : null")
        assertThat(nullableString?.length?.toLong() ?: 0).isEqualTo(0)
    }

    @Test
    fun nullableNonNull() {
        var nullableString: String?
        nullableString = "sample"

        assertThat("length is : ${nullableString.length}").isEqualTo("length is : 6")
        assertThat(nullableString?.length?.toLong() ?: 0).isEqualTo(6)
    }
}
