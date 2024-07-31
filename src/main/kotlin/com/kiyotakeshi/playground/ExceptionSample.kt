package com.kiyotakeshi.playground

import java.lang.Exception

fun nameLength(name: String?): Int? {
    return try {
        name!!.length
    } catch (ex: Exception) {
        println("exception is : $ex")
        null
    }
}
