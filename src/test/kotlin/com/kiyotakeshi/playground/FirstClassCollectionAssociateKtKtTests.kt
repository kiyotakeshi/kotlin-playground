package com.kiyotakeshi.playground

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FirstClassCollectionAssociateKtKtTests {

    private fun createOrders() = listOf(
        Order(1, "Order1"),
        Order(2, "Order2"),
        Order(3, "Order3"),
        Order(4, "Order4"),
    )

    private fun createOrderItems() = listOf(
        OrderItem(1, "Item1"),
        OrderItem(1, "Item2"),
        OrderItem(2, "Item3"),
        OrderItem(3, "Item4"),
        OrderItem(3, "Item5")
    )

    private fun createOrderItemLists(
        orders: List<Order>,
        orderItems: List<OrderItem>,
    ) = orders.map { order ->
        OrderItemList(order.id, orderItems.filter { it.orderId == order.id })
    }

    @Test
    fun `first class collection と associateBy, associateWith を組み合わせる`() {
        // arrange
        val orders = createOrders()
        val orderItems = createOrderItems()
        val orderItemLists = createOrderItemLists(orders, orderItems)
        orderItemLists.forEach { println(it) }

        // act
        val orderToOrderItemListMap = createOrderToOrderItemListMap(orders, orderItemLists)

        // assertion
        println("Order to Order Item List Map: $orderToOrderItemListMap")
        assertThat(orderToOrderItemListMap.keys).containsExactly(
            Order(1, "Order1"),
            Order(2, "Order2"),
            Order(3, "Order3"),
            Order(4, "Order4"),
        )
        assertThat(orderToOrderItemListMap.values).containsExactlyInAnyOrder(
            listOf(OrderItem(1, "Item1"), OrderItem(1, "Item2")),
            listOf(OrderItem(2, "Item3")),
            listOf(OrderItem(3, "Item4"), OrderItem(3, "Item5")),
            emptyList()
        )
    }

    @Test
    fun `first class collection の key を活用せず flatMap で処理している`() {
        // arrange
        val orders = createOrders()
        val orderItems = createOrderItems()
        val orderItemLists = createOrderItemLists(orders, orderItems)

        // act
        val orderToOrderItemListMap = createOrderToOrderItemListMapWithFlatMap(orders, orderItemLists)

        // assertion
        println("Order to Order Item List Map: $orderToOrderItemListMap")
        assertThat(orderToOrderItemListMap.keys).containsExactly(
            Order(1, "Order1"),
            Order(2, "Order2"),
            Order(3, "Order3"),
            Order(4, "Order4"),
        )
        assertThat(orderToOrderItemListMap.values).containsExactlyInAnyOrder(
            listOf(OrderItem(1, "Item1"), OrderItem(1, "Item2")),
            listOf(OrderItem(2, "Item3")),
            listOf(OrderItem(3, "Item4"), OrderItem(3, "Item5")),
            emptyList()
        )
    }
}
