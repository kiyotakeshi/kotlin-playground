package com.kiyotakeshi.playground

const val KAFKA = "Kafka"
const val BUSINESS_ANALYTICS = "Business Analytics"
const val HEALTH_COOKING = "Healthy Cooking"
const val SPRING_BOOT = "SpringBoot"
const val JAVA = "java"
const val REACTIVE_PROGRAMMING = "Reactive Programming"
const val MULTI_THREADING = "MultiThreading"
const val INTEGRATION_TESTING = "Integration Testing"

data class Course(
    val id: Int,
    val name: String,
    val category: CourseCategory,
    val tags: List<String>
)

data class Student(
    val name: String,
    val age: Int,
)

enum class CourseCategory{
    DEVELOPMENT,
    BUSINESS,
    HOBBY
}

data class Instructor(
    val name: String,
    val age: Int,
    val noOfCourses : Int,
    val coursesTaught : List<Course>
)


fun createCourses(): MutableList<Course> {

    return mutableListOf(
        Course(
            id = 1,
            name = "Reactive MicroServices using Spring Boot(WebFlux)",
            category = CourseCategory.DEVELOPMENT,
            tags = mutableListOf(REACTIVE_PROGRAMMING, JAVA, SPRING_BOOT)
        ),
        Course(
            2,
            "Learn Java 17 Features By coding it",
            CourseCategory.DEVELOPMENT,
            mutableListOf(JAVA)
        ),
        Course(
            3,
            "Healthy Cooking",
            CourseCategory.HOBBY,
            mutableListOf(HEALTH_COOKING)
        ),
        Course(
            4,
            "Apache Kafka hands-on with command line",
            CourseCategory.DEVELOPMENT,
            mutableListOf(KAFKA)
        ),
        Course(
            5,
            "Reactive Programming using Project Reactor",
            CourseCategory.DEVELOPMENT,
            mutableListOf(REACTIVE_PROGRAMMING, JAVA)
        ),
        Course(
            6,
            "Apache Kafka hands-on using SpringBoot",
            CourseCategory.DEVELOPMENT,
            mutableListOf(KAFKA, JAVA, SPRING_BOOT)
        ),
        Course(
            7,
            "JUnit5 for Java Developers",
            CourseCategory.DEVELOPMENT,
            mutableListOf(JAVA, INTEGRATION_TESTING)
        ),
        Course(
            8,
            "Product Management",
            CourseCategory.BUSINESS,
            mutableListOf(BUSINESS_ANALYTICS)
        ),
        Course(
            9,
            "Parallel and Asynchronous Coding Modern Java ",
            CourseCategory.DEVELOPMENT,
            mutableListOf(JAVA, MULTI_THREADING)
        )
    )
}
