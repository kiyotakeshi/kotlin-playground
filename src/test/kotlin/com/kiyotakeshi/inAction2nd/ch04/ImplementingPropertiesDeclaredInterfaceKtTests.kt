package com.kiyotakeshi.inAction2nd.ch04

import org.junit.jupiter.api.Test

class ImplementingPropertiesDeclaredInterfaceKtTests {
    @Test
    fun print() {
        val socialUser = SocialUser(12345)
        println(socialUser.accounId)
        println(socialUser.nickname)
        println(PrivateUser("test@example.com").nickname)
        println(SubscribingUser("test@example.com").nickname)
    }
}
