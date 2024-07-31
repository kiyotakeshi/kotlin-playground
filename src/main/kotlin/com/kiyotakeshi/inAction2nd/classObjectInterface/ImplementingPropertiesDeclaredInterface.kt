package com.kiyotakeshi.inAction2nd.classObjectInterface

interface User {
    // an interface can contain abstract property declarations
    val nickname: String
}

class PrivateUser(override val nickname: String) : User

class SubscribingUser(val email: String) : User {
    override val nickname: String
        get() = email.substringBefore('@')
}

private fun getNameFromSocialNetwork(accounId: Int): String = "soc:$accounId"

class SocialUser(val accounId: Int) : User {
    override val nickname: String
        // custom getter
        get() = getNameFromSocialNetwork(accounId)
}

