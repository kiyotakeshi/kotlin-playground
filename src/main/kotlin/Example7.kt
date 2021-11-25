fun main() {
    println(max(1, 2)) // 2

    // ----------------------------------
    println()
    val rectangle = Rectangle(30, 30)
    // 引数のない関数とカスタムアクセサを持つプロパティを使うかの違いは可読性だけ
    // あるクラスの特徴を表現したい場合はプロパティとして宣言するべき
    println(rectangle.isSquare) // true

    // ----------------------------------
    println()
    // 1..30 は range と呼ばれ、 range は閉じて包括的(2つ目の値がレンジの中に含まれる)
    // range 内のすべての値を通して繰り返し処理が行える場合、 progression(数列)と呼ぶ
    for (i in 1..30) {
    // for (i in 30 downTo 1 step 2) { // 100 から -2 ずつ step する
        print(fizzBuzz(i))
    }
}
// {} に本体が入る形で関数が記述されている場合は、ブロック本体(block body)を持つ
// 式を直接返すのであれば、式本体(expression body)を持つ
// block body <-> expression body の変換は IDE の補完で可能
//fun max(a: Int, b: Int): Int {
//    // if は結果となる値を返す式(java だと三項演算子を使う)
//    // kotlin の制御構文の多くは文ではなく式
//    // 式は別の式の一部として使用可能
//    return if (a > b) a else b
//}

// expression body で型推論(type inference)を使って書いた場合使って書いた場合使って書いた場合
fun max(a: Int, b: Int) = if (a > b) a else b

// カスタムアクセサ
// 正方形かを判定できればいいので個別のフィールドに値を格納する必要がない
class Rectangle(val height: Int, val width: Int) {
    // isSquare プロパティは値をフィールドに格納していない
    val isSquare: Boolean
        get() {
            return height == width
        }
}

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 3 == 0 -> "Fizz "
    i % 5 == 0 -> "Buzz "
    else -> "$i "
}
