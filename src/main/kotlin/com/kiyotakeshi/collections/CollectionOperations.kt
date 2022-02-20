package com.kiyotakeshi.collections

import com.kiyotakeshi.sampleData.Course
import com.kiyotakeshi.sampleData.CourseCategory
import com.kiyotakeshi.sampleData.courseList

fun main() {
    val courseList = courseList()
    val developmentPredicate: (Course) -> Boolean = { it.category == CourseCategory.DEVELOPMENT }

    courseFilter(courseList, developmentPredicate)
}

fun courseFilter(
    courseList: MutableList<Course>,
    developmentPredicate: (Course) -> Boolean
) {
    courseList
        // .filter { it.category == CourseCategory.DEVELOPMENT }
        // .filter(developmentPredicate)
        .filter{ developmentPredicate.invoke(it)}
        .forEach { println("development courses : $it") }
}
