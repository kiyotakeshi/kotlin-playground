@file:Suppress("MatchingDeclarationName")

package com.kiyotakeshi.inAction2nd.ch04.inner

class User private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String) =
            User(email.substringBefore('@'))

        fun newSocialUser(accountId: Int) =
            User("soc:$accountId")
    }
}
