package com.kiyotakeshi.playground

data class Person(var name: String, var age: Int = 0, var city: String = "") {
    fun moveTo(newCity: String) {
        city = newCity
    }

    fun incrementAge() {
        age++
    }
}

data class Headers(val headerInfo: String)

data class Response(val headers: Headers)

data class RestClient(val url: String) {
    fun getResponse() = Response(Headers("some header info"))
}

class Logger {
    var called: Boolean = false

    fun info(message: String) {
        println("Message is: $message")
        called = true
    }

    fun wasCalled() = called
}

fun processNonNullString(s: String): String {
    println("call processNonNullString")
    return "processing non null string: $s"
}

fun getMessageWithLet(s: Any?): String {
    return s?.let {
        "value was not null: $it"
    } ?: "value was null"
}

fun getMessageWithRun(s: Any?): String {
    return s?.run {
        "value was not null: $this"
    } ?: "value was null"
}

// str.takeIf { it.contains("w") } ?: println("no 'w' in $str")
fun takeIfContainsW(s: String): String {
    return s.takeIf { it.contains("w") } ?: "no 'w' in $s"
}
