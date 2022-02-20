package com.kiyotakeshi.collections

import com.kiyotakeshi.sampleData.Course
import com.kiyotakeshi.sampleData.CourseCategory
import com.kiyotakeshi.sampleData.SPRING_BOOT
import com.kiyotakeshi.sampleData.courseList

fun main() {
    val courseList = courseList()
    val developmentPredicate: (Course) -> Boolean = { it.category == CourseCategory.DEVELOPMENT }
    val hobbyPredicate: (Course) -> Boolean = { it.category == CourseCategory.HOBBY }

    courseFilter(courseList, developmentPredicate)

    println("---------------------")
    courseMap(courseList, hobbyPredicate)

    println("---------------------")
    val list: List<List<Int>> = listOf(listOf(1, 2, 3), listOf(4, 5, 6))
    val result = list.map { outerList ->
        outerList.map {
            it.toDouble()
        }
    }
    println("result : $result") // result : [[1.0, 2.0, 3.0], [4.0, 5.0, 6.0]]

    val flatMap = list.flatMap { outerList ->
        outerList.map {
            it.toDouble()
        }
    }
    println("result : $flatMap") // result : [1.0, 2.0, 3.0, 4.0, 5.0, 6.0]

    println("---------------------")
    val courses = courseFlatMap(courseList, SPRING_BOOT)
    println("courses : $courses")

    println("---------------------")
    collectionsNullability()
}

fun collectionsNullability() {
    var list: MutableList<String>? = null
    list = mutableListOf()
    list.add("mike")
    list.forEach { println("value is : $it") }

    val list1: List<String?> = listOf("popcorn", null, "kendrick")
    list1.forEach { println("value is : ${it?.length}") }
}


fun courseFilter(
    courseList: MutableList<Course>,
    developmentPredicate: (Course) -> Boolean
) {
    courseList
        // .filter { it.category == CourseCategory.DEVELOPMENT }
        // .filter(developmentPredicate)
        .filter { developmentPredicate.invoke(it) }
        .forEach { println("development courses : $it") }
}

fun courseMap(
    courseList: MutableList<Course>,
    hobbyPredicate: (Course) -> Boolean
) {
    val courses = courseList
        // .filter(hobbyPredicate)
        .filter { hobbyPredicate.invoke(it) }
        .map { "${it.name} - ${it.category}" }
        .forEach { println(it) }
}

fun courseFlatMap(courseList: MutableList<Course>, tag: String): List<String> {
    val specificTagCourses = courseList()
        .flatMap { course ->
            val courseName = course.name
            course.tags.filter {
                it == tag
            }.map { courseName }
        }
    return specificTagCourses
}
