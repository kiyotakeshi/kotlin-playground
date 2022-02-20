package com.kiyotakeshi.functions

fun main() {
    letSample()
}

fun letSample() {
    val numbers = mutableListOf(1, 2, 3, 4)
    // val result = numbers.map { it * 2 }.filter { it > 5 }
    // println(result)
    val result = numbers
        .map { it * 2 }
        .filter { it > 5 }
        .let {
            println(it)
            it.sum()
        }
    println(result)

    println("---------------------")
    var name: String? = null
    // name = "mike"
    val result1 = name?.let {
        println(it)
        it.uppercase()
    }
    println(result1)
}
