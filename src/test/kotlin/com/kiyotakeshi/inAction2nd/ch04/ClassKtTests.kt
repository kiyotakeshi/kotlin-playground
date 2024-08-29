package com.kiyotakeshi.inAction2nd.ch04

import com.kiyotakeshi.playground.Human
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.HashSet

class ClassKtTests {

    @Test
    fun `hashCode not impl`() {
        val user1 = PrivateUser("mike")
        val processed: HashSet<PrivateUser> = hashSetOf(user1)

        val user2 = PrivateUser("mike")
        assertThat(processed.contains(user2)).isFalse()
        // user1, user2 で別の hashCode
        println(user1.hashCode())
        println(user2.hashCode())

        val processed2 = setOf(PrivateUser("mike"))
        assertThat(processed2.contains(PrivateUser("mike"))).isFalse()
    }

    @Test
    fun copy() {
        val mike = Human(1, "mike")
        val mike2 = Human(1, "mike")
        assertThat(mike).isEqualTo(mike2)
        assertThat(mike.copy(id = 2)).isNotEqualTo(mike2)
    }
}
