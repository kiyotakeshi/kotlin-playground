fun main() {
    val list = arrayListOf(1, 2, 3)
    println(list.joinToString(", ", prefix = "(", postfix = ")"))
    // ----------------------------------
}

// ライブラリの標準実装であるが自身で定義する場合
// extension function(拡張関数)を定義する
//fun <T> Collection<T>.joinToString(
//    separator: String = ", ",
//    prefix: String = "",
//    postfix: String = ""
//) {
//    val result = StringBuffer(prefix)
//    for ((index, element) in this.withIndex()) {
//        if (index > 0) result.append(separator)
//        result.append(element)
//    }
//}

