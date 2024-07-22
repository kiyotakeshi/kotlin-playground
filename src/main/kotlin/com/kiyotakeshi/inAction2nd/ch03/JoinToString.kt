package com.kiyotakeshi.inAction2nd.ch03

fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    suffix: String = ""
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {
        if(index > 0) result.append(separator)
        result.append(element)
    }
    result.append(suffix)
    return result.toString()
}

// receiver type を具体的に記述することも可能
fun Collection<String>.join(
    separator: String = ", ",
    prefix: String = "",
    suffix: String = ""
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {
        if(index > 0) result.append(separator)
        result.append(element)
    }
    result.append(suffix)
    return result.toString()
}