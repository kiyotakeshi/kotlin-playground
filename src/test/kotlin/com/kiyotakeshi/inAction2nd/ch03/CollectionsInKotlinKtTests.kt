package com.kiyotakeshi.inAction2nd.ch03

import org.junit.jupiter.api.Test

class CollectionsInKotlinKtTests {
    @Test
    fun vargs() {
        val strings: Array<String> = arrayOf("1", "2", "3")
        spreadOperator(strings)
    }
}
