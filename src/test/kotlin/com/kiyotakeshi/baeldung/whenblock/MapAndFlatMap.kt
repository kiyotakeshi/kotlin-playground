@file:Suppress("MatchingDeclarationName")

package com.kiyotakeshi.baeldung.whenblock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MapAndflatMap {
    data class Order(val lines: List<OrderLine>)
    data class OrderLine(val name: String, val price: Int)

    @Test
    fun `flatMap should flatten the one to many relation`() {
        var orders = listOf(
            Order(listOf(OrderLine("coffee", 1), OrderLine("cake", 2))),
            Order(listOf(OrderLine("tomato", 3), OrderLine("coffee", 4))),
            Order(listOf(OrderLine("cake", 5), OrderLine("chocolate", 6)))
        )
        // val map: List<List<OrderLine>> = orders.map { it.lines }
        // 各注文とその注文行の間の一対多の関係をフラットにする
        val names = orders.flatMap { it.lines }
            .map { it.name }
            .distinct()
        assertThat(names).containsExactlyInAnyOrder("coffee", "cake", "tomato", "chocolate")
    }
}
