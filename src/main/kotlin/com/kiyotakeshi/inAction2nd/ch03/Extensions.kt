package com.kiyotakeshi.inAction2nd.ch03

// extension function
// `String` is receiver type
// `this` is receiver object
fun String.lastChar(): Char = this.get(this.length - 1)

// extension property
// > プロパティと呼ばれていますが、状態を格納する適切な場所がないため、状態を持つことはできません
val String.lastChar: Char
    // 状態を格納はできないので custom accessor を定義する必要がある
    get() = get(length - 1)

var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value) {
        setCharAt(length - 1, value)
    }
