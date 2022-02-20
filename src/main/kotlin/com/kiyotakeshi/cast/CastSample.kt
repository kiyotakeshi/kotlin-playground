package com.kiyotakeshi.cast

import com.kiyotakeshi.classes.Course

fun main() {
    val course = Course(1, "kotlin first step", "mike popcorn")
    checkType(course)
    checkType("sample")
    castNumber(1.0)
    castNumber(1)
}

fun castNumber(any: Any) {
    when(any){
        any as? Double -> println("value is Double type")
        any as? Int -> println("value is Int type")
    }
}

fun checkType(type: Any) {
    when (type) {
        is Course -> {
            // you don't have to cast(redundant)
            // one the condition is satisfied, it automatically converts to appropriate type(smart casting)
            // val course = type as Course
            println(type)
        }
        is String -> {
            println(type.uppercase())
        }
    }
}
