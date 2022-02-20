package com.kiyotakeshi.playground

import com.kiyotakeshi.functions.courseName
import com.kiyotakeshi.functions.topLevelFunction
import java.time.LocalDate

fun main() {
    printPerson("mike", "mike@example.com", LocalDate.parse("2022-02-19"))
    printPerson(name = "mike", birthday = LocalDate.parse("2022-02-20")) // named argument

    println(
        """
            ABC
            CBA
        """.trimIndent()
    )

    println(topLevelFunction());
    println("course name: $courseName")
}

fun printPerson(
    name: String,
    email: String = "", // default value parameter
    birthday: LocalDate = LocalDate.now()
) {
    println("hello $name and email is $email and birthday is $birthday")
}
