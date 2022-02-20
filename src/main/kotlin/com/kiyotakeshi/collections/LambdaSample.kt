package com.kiyotakeshi.collections

// higher order function accepts another function
fun calculate(
    x: Int,
    y: Int,
    op: (x: Int, y: Int) -> Int
): Int {
    return op(x, y)
}

fun main() {
    // val addLambda: (Int) -> Int = { x: Int -> x + x }
    val addLambda = { x: Int -> x + x }
    println("add result : ${addLambda(2)}")

    // val multiplyLambda: (Int, Int) -> Int = { x: Int, y: Int -> x * y }
    val multiplyLambda = { x: Int, y: Int ->
        println("x is $x and y is $y")
        x * y
    }
    println("multiply result : ${multiplyLambda(3, 4)}")

    println("---------------------")

    val higherOrderFunction = calculate(2, 3, { a, b -> a * b })
    println("higher order function result is $higherOrderFunction")

    val higherOrderFunctionAdd = calculate(2, 3) { a, b -> a + b }
    println("higher order function result is $higherOrderFunctionAdd")
}
