package com.kiyotakeshi.interfaces

import com.kiyotakeshi.classes.Course

interface CourseRepository {
    // override してほしい field を指定できる
    val isCoursePersisted: Boolean
    fun getById(id: Int): Course

    // interface だけど実装をかけるみたい(Java でいう default にあたる)
    fun save(course: Course): Int {
        println("save course : $course")
        return course.id
    }
}

interface Repository {
    fun getAll(): Any
}

// allowed to have a class implement multiple interface
class SqlCourseRepository : CourseRepository, Repository {
    override var isCoursePersisted: Boolean = false

    override fun getById(id: Int): Course {
        return Course(id, "kotlin first step", "mike popcorn")
    }

    override fun getAll(): Any {
        return 1
    }
}

class NoSqlCourseRepository : CourseRepository {
    override var isCoursePersisted: Boolean = false

    override fun getById(id: Int): Course {
        return Course(id, "getting start MongoDB", "mike popcorn")
    }

    override fun save(course: Course): Int {
        isCoursePersisted = true
        println("save course in NoSQL : $course")
        return course.id
    }
}

fun main() {
    val sqlCourseRepository = SqlCourseRepository()
    val course = sqlCourseRepository.getById(100)
    println("course is $course")
    val savedCourseId = sqlCourseRepository.save(Course(333, "terraform in action", "kendrick west"))
    println("saved course Id is : $savedCourseId")
    println("course persisted value is : ${sqlCourseRepository.isCoursePersisted}")

    println()
    val noSqlCourseRepository = NoSqlCourseRepository()
    val course2 = noSqlCourseRepository.getById(3)
    println("course is $course2")

    val savedCourseId2 = noSqlCourseRepository.save(Course(643, "kubernates in action", "west kendrick"))
    println("saved course Id is : $savedCourseId2")
    println("course persisted value is : ${noSqlCourseRepository.isCoursePersisted}")
}
