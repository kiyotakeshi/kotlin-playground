package com.kiyotakeshi

import java.lang.Exception

fun main() {
    println("name length is : ${nameLength("mike")}")
    println("name length is : ${nameLength(null)}")
}

fun nameLength(name: String?): Int? {
    // lift return out of try
    // try block is also an expression
//    try {
//        return name!!.length
    return try {
        name!!.length
    } catch (ex: Exception) {
        println("exception is : $ex")
        null
    }
}
