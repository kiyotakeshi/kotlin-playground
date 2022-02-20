package com.kiyotakeshi.classes

open class User(val name: String) {
    open var isLoggedIn: Boolean = false
    open fun login() {
        println("login")
    }
    private fun secret(){
        println("secret")
    }

    protected open fun logout(){
        println("logout")
    }
}

class Student(name: String) : User(name) {
    override fun login() {
        super.login()
        println("student login")
    }

    companion object {
        const val numberOfEnrolledCourses = 5
        fun country() = "Japan"
    }

    // 親が public ではないので明示的に記載が必要
    public override fun logout(){
        super.logout()
    }
}

class Teacher(name: String) : User(name) {
    override var isLoggedIn: Boolean = true
    override fun login() {
        println("teacher login")
    }
}

fun main() {
    val student = Student("mike")
    println("name is : ${student.name}")
    student.login()
    println("logged in value is : ${student.isLoggedIn}")
    println("country is : ${Student.country()}")
    println("number of enrolled courses is : ${Student.numberOfEnrolledCourses}")
    student.logout()

    println()
    val teacher = Teacher("popcorn")
    println("name is : ${teacher.name}")
    teacher.login()
    println("logged in value is : ${teacher.isLoggedIn}")

    val user = User("yamada")
    // user.secret()
    // user.logout()
}
