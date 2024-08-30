package com.kiyotakeshi.playground

data class Order(val id: Int, val name: String)

data class OrderItem(val orderId: Int, val name: String)

data class OrderItemList(val orderId: Int, val items: List<OrderItem>)

fun createOrderToOrderItemListMap(
    orders: List<Order>,
    orderItemLists: List<OrderItemList>,
): Map<Order, List<OrderItem>> {
    // first class collection である OrderItemList の orderId を key にして、その中の items を value にしている
    val orderItemListMap = orderItemLists.associateBy { it.orderId }

    return orders.associateWith { order ->
        // map の key を指定してアクセスし、ヒットしたら value が返され、ヒットしなかったら null が返る特性を利用する
        // ヒットしないことがある = nullable なデータのため、 safe call operator(`?.`) と elvis operator(`?:`) とを組み合わせて処理している
        orderItemListMap[order.id]?.items ?: emptyList()
    }
}

fun createOrderToOrderItemListMapWithFlatMap(
    orders: List<Order>,
    orderItemLists: List<OrderItemList>,
): Map<Order, List<OrderItem>> {
    return orders.associateWith { order ->
        // すべての List<OrderItem> の flatten してからその中の orderId で filter た結果を Map の value にしている
        // first class collection である OrderItemList の orderId を活用できていないので、あまり筋の良くない実装
        orderItemLists.flatMap { it.items }
            .filter { it.orderId == order.id }
    }
}
