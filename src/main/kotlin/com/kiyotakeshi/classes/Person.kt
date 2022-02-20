package com.kiyotakeshi.classes

class Person(
    val name: String = "",
    val age: Int = 0
) {
    var email: String = ""
    var nameLength: Int = 0

    // secondary constructor
    constructor(
        _email: String,
        _name: String = "",
        _age: Int = 0
    ) : this(_name, _age) {
        email = _email
    }

    init {
        println("initialization logic")
        nameLength = name.length
    }

    fun action() {
        println("say hello")
    }
}

fun main() {
    /*
    val person = Person("mike", 29)
    person.action()
    println("name: ${person.name}, age: ${person.age}")

    val person2 = Person()
    println("name: ${person2.name}, age: ${person2.age}")
     */

    val person3 = Person(_email = "test@example.com", "yamada", 33)
    println("name: ${person3.name}(${person3.nameLength} length), age: ${person3.age}, email: ${person3.email}")
}
