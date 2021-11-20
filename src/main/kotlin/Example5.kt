fun main() {
    collectionFind(listOf(0, 1, 2, 3, 4)) // 0
    collectionFindLast(listOf(0, 1, 2, 3, 4)) // 4
    collectionFilter(listOf(0, 1, 2, 3, 4, 5, 6)) // [0, 2, 4, 6]
    collectionFilterNot(listOf(0, 1, 2, 3, 4, 5, 6)) // [1, 3, 5]
    collectionMap(listOf(0, 1, 2, 3)) // [0, 2, 4, 6]
    collectionMethod()
}

fun collectionFind(numbers: List<Int>) {
    // 定数に代入することで、後から値が追加されることを防げるため安全性が増す
    val firstEvenValue = numbers.find { it % 2 == 0 }
    println(firstEvenValue)
}

fun collectionFindLast(numbers: List<Int>) {
    val lastEvenValue = numbers.findLast { it % 2 == 0 }
    println(lastEvenValue)
}

fun collectionFilter(numbers: List<Int>) {
    val evenList = numbers.filter { it % 2 == 0 }
    println(evenList)
}

fun collectionFilterNot(numbers: List<Int>) {
    val evenList = numbers.filterNot { it % 2 == 0 }
    println(evenList)
}

fun collectionMap(numbers: List<Int>) {
    val result = numbers.map { it * 2 }
    println(result)
}

fun collectionMethod() {
    // 0以上の要素を2倍する
    // 0未満の要素は null に置き換える
    // null ではない要素のみを取り出す
    val numbers = listOf(-2, -1, 0, 1, 2)

    // 各要素に対して操作を行い、 null ではない要素のみを取り出す
    // val result = numbers.map { if (it >= 0) it * 2 else null }.filter { it != null }
    // val result = numbers.map { if (it >= 0) it * 2 else null }.filterNotNull()
    val result = numbers.mapNotNull { if (it >= 0) it * 2 else null }
    println(result) // [0, 2, 4]
}
