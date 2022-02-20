package com.kiyotakeshi.collections

import com.kiyotakeshi.sampleData.Course
import com.kiyotakeshi.sampleData.CourseCategory
import com.kiyotakeshi.sampleData.courseList

fun main() {
    val namesListUsingSequence = listOf("mike", "popcorn", "yamada", "kendrick", "west")
        .asSequence()
        .filter { it.length >= 4 }
        .map { it.uppercase() }
        .toList()
    println("names list using sequence : $namesListUsingSequence")

    println("---------------------")
    val developmentPredicate: (Course) -> Boolean = { it.category == CourseCategory.DEVELOPMENT }
    courseFilterUsingSequence(courseList(), developmentPredicate)

    println("---------------------")
    val range = 1..1000_000_000

    // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
//    range.map { it.toDouble() }
//        .forEach { println("value is : $it") }

    range
        .asSequence() // sequence doesn't have the concept of intermediate collection
        .map { it.toDouble() }
        // .take(100)
        .forEach { println("value is : $it") }
}

fun courseFilterUsingSequence(
    courseList: MutableList<Course>,
    developmentPredicate: (Course) -> Boolean
) {
    courseList
        .asSequence()
        .filter(developmentPredicate)
        .filter { developmentPredicate.invoke(it) }
        .forEach { println("development courses : $it") }
}
