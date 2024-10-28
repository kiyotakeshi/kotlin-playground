package com.kiyotakeshi.inAction2nd.ch10

fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = "",
    // lambda を引数として受け取る high order function
    // デフォルトの挙動は .toString() に指定しておく
    // T なので collection 内の要素の型パラメータを持つ
    transform: (T) -> String = { it.toString() }
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(transform(element))
    }
    result.append(postfix)
    return result.toString()
}

enum class Delivery { STANDARD, EXPEDITED }

class Order(val itemCount: Int)

@Suppress("MagicNumber")
fun getShippingCostCalculator(delivery: Delivery): (Order) -> Double {
    if (delivery == Delivery.EXPEDITED) {
        return { order -> 10 + 2.0 * order.itemCount }
    }
    return { order -> 1.4 * order.itemCount }
}

data class Person(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String? = null
)

class ContactListFilters {
    var prefix: String = ""
    var onlyWithPhoneNumber: Boolean = false

    fun getPredicate(): (Person) -> Boolean {
        val startsWithPrefix = { p: Person ->
            p.firstName.startsWith(prefix) || p.lastName.startsWith(prefix)
        }
        if (!onlyWithPhoneNumber) {
            return startsWithPrefix
        }
        return {
            startsWithPrefix(it) &&
                it.phoneNumber != null
        }
    }
}

fun lookForBob(people: List<Person>) {
    people.forEach {
        if (it.firstName == "bob") {
            println("found bob")
        }
        return
    }
    // return が lookForBob に対してなので、この行は実行されない
    println("bob is not found")
}

fun lookForBobWithLabel(people: List<Person>) {
    people.forEach {
        // lambda を引数として受け取る関数名(=forEach) をラベルとして指定して return する
        if (it.firstName != "bob") return@forEach
        println("found bob")
    }
}
