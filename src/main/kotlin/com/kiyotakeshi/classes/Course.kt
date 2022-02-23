package com.kiyotakeshi.classes

import CourseInJava

data class Course(
    val id: Int,
    val name: String,
    val author: String,
    val courseCategory: CourseCategory = CourseCategory.DEVELOPMENT
)

enum class CourseCategory {
    DEVELOPMENT,
    BUSINESS,
    HOBBY,
}

fun main() {
    val course = Course(1, "kotlin first step", "mike popcorn")
    val course2 = Course(1, "kotlin first step", "mike popcorn")
    println(course)
    println("object equality: ${course == course2}")

    val course3 = Course(3, "how to increase sales", "yamada", CourseCategory.BUSINESS);
    println(course3)

    val courseInJava = CourseInJava(2, "migrate java to kotlin", "none")
    println("courseInJava : $courseInJava")
}
