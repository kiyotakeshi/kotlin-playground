package com.kiyotakeshi.inAction2nd.ch03

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RegexInKotlinKtTests {
    @Test
    fun parse() {
        val parsePath = parsePath("/Users/takeshi-kiyota/kotlin-playground/memo.md")
        assertThat(parsePath)
            .isEqualTo("dir: /Users/takeshi-kiyota/kotlin-playground, name: memo, ext: md")
    }

    @Test
    fun parseWithRegex() {
        val parsePath = parsePathRegex("/Users/takeshi-kiyota/kotlin-playground/memo.md")
        assertThat(parsePath)
            .isEqualTo("dir: /Users/takeshi-kiyota/kotlin-playground, name: memo, ext: md")
    }
}
