package com.kiyotakeshi.inAction2nd.ch03

val set = setOf(1, 7, 7, 53)
val list = listOf(1, 7, 7, 53)
val map = mapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

fun main() {
    println(set) // [1, 7, 53]
    println(list) // [1, 7, 7, 53]
    println(map) // {1=one, 7=seven, 53=fifty-three}

    // kotlin は標準の Java collection class を使用している
    println(set.javaClass) // class java.util.LinkedHashSet
    println(list.javaClass) // class java.util.Arrays$ArrayList
    println(set.javaClass) // class java.util.LinkedHashSet
}