package com.kiyotakeshi.inAction2nd.ch02

import java.io.BufferedReader

fun readNumber(reader: BufferedReader): Int? {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        null
    }
    return number
}
