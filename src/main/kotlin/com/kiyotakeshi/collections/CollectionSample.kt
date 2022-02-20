package com.kiyotakeshi.collections

fun main() {
    val names = listOf("mike", "popcorn", "yamada")
    // names.add() できない(immutable list)
    println("names : $names")

    val namesMutableList = mutableListOf("mike", "popcorn", "yamada")
    namesMutableList.add("taro")
    println("names mutable list : $namesMutableList")

    println("---------------------")

    val set = setOf("mike", "popcorn", "yamada", "yamada")
    println("set $set")

    val mutableSetOf = mutableSetOf("mike", "popcorn", "yamada")
    mutableSetOf.add("yamada")
    mutableSetOf.add("mike")
    println("mutable set :  $mutableSetOf")

    println("---------------------")
    val nameAgeMap = mapOf("mike" to 27, "popcorn" to 33)
    println("name age map : $nameAgeMap")

    val nameAgeMutableMap = mutableMapOf("mike" to 27, "popcorn" to 33)
    nameAgeMutableMap["yamada"] = 41
    println("name age mutable map : $nameAgeMutableMap")

    nameAgeMap
        .forEach { (k, v) ->
            println("key $k and the value is $v")
        }

    val value = nameAgeMap.getOrElse("bike") {"not found"}
    println("value $value")

    val filteredMap = nameAgeMap
        .filterKeys { it.length > 5 }
        .map { it.key.uppercase() }
    println("filtered map : $filteredMap")

    val maxAge = nameAgeMap
        .maxOfOrNull { it.value }
    println("max age : $maxAge")
}
