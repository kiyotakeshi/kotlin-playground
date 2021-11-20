fun main() {
    collectionAny(listOf(0, 1, 2, 3, 4))
    collectionNone(listOf(0, 1, 2, 3, 4))
    println(handleResult(true))
    whenExpression()
    elvisExpression()
}

private fun collectionAny(numbers: List<Int>) {
    // 任意の条件を満たす要素があるかの判定が直感的
    val isContainsEven = numbers.any { it % 2 == 0 }
    println(isContainsEven) // true
}

fun collectionNone(numbers: List<Int>) {
    val isNotContainsEven = numbers.none { it % 2 == 0 }
    println(isNotContainsEven) // false
}

fun handleResult(isSuccess: Boolean): String {
    // if が式(expression)のため変数ではなく定数として定義できる
    val result = if (isSuccess) {
        "success"
    } else {
        "failure"
    }
    return result
}

fun whenExpression() {
    val name = "jiro"
    val age = 0

    // when も式(expression)
    val message = when {
        name == "taro" -> "name is taro"
        age >= 20 -> "over 20"
        else -> "other"
    }
    println(message)
}

fun elvisExpression(){
    val result: String? = null
    // println(if (result != null) result else "")
    // ?: で null かどうかをチェックしている(エルビス演算子)
    println(result ?: "result is null") // result is null
}
