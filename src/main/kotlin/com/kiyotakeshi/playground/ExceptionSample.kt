package com.kiyotakeshi.playground

import java.lang.Exception

class User {
    lateinit var name: String

    val isValidName: Boolean
        get() = name != ""
}

data class Human(val id: Int, var name: String)

fun nameLength(name: String?): Int? {
    return try {
        name!!.length
    } catch (ex: Exception) {
        println("exception is : $ex")
        null
    }
}
