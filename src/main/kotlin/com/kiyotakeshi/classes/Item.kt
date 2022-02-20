package com.kiyotakeshi.classes

class Item() {
    var name: String = ""
    var price: Double = 0.0
    get() {
        println("call getter")
        return field
    }

    set(value) {
        if(value >= 0.0){
            field = value
        } else {
            throw IllegalArgumentException("Negative price is not allowed")
        }
    }

    constructor(_name: String) : this() {
        name = _name
    }
}

fun main() {
    val item = Item("macbook")
    println("item name is ${item.name}")
    item.name = "macbook pro"
    println("item name is ${item.name}")

    item.price = 145.0
    println(item.price)
}
