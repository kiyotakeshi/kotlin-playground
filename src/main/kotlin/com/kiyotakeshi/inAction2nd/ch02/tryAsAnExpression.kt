package com.kiyotakeshi.inAction2nd.ch02

import java.io.BufferedReader
import java.io.Serializable

//
fun readNumber(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        null
    }
    println(number)
}