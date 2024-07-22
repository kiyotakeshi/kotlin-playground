package com.kiyotakeshi.inAction2nd.ch02

enum class Color(
    val r: Int, val g: Int, val b: Int
) {
    RED(255, 0, 0),
    BLUE(0, 0, 255),
    GREEN(0, 255, 0);

    val rgb = (r * 256 + g) * 256 + b
    fun printColor() = println("$this is $rgb")
}

// mnemonic は、覚えやすいように情報を簡略化したり、記憶を助けるための手法や装置を指します。
// プログラミングにおいては、特定の操作や値を覚えやすくするための短縮形やキーワードを指すことが多いです。
fun getMnemonic(color: Color) =
    when (color) {
        Color.RED -> "Richard"
        Color.GREEN -> "Gave"
        Color.BLUE -> "Battle"
    }