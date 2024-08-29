package com.kiyotakeshi.inAction2nd.ch04.inner

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UserTests {
    @Test
    fun `create new user`() {
        val subscribingUser = User.newSubscribingUser("bob@gmail.com")
        val socialUser = User.newSocialUser(4)
        assertThat(subscribingUser.nickname).isEqualTo("bob")
        assertThat(socialUser.nickname).isEqualTo("soc:4")
    }
}
