@file:Suppress("ImportOrdering")

package com.kiyotakeshi.inAction2nd.ch03.inner

// extension function が package 外の場合は import が必要
import com.kiyotakeshi.inAction2nd.ch03.lastChar

// as を使って関数名を変更できる
// import com.kiyotakeshi.inAction2nd.ch03.lastChar as last

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ExtensionsKtTests {
    @Test
    fun lastChar() {
        assertThat("Kotlin".lastChar()).isEqualTo('n')
        // assertThat("Kotlin".last()).isEqualTo('n')
    }
}
