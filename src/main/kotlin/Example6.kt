fun main() {
//    val sum = listOf(0, 1, 2, 3, 4).sum()
//    println(sum)
//    println(sum * 2)

    // let はスコープ関数
    // ブロック内では it という定数に代入されている
    // 値の使用範囲や影響範囲が明確になる
    // 定数名を考えなくても良い
    listOf(0, 1, 2, 3, 4).sum().let {
        // it = 0 // Val cannot be reassigned
        println(it) // 10
        println(it * 2) // 20
        println(it * 4) // 40
    }

    // 定数名を変える場合は引数に渡す時に指定
    listOf(0, 1, 2, 3, 4).sum().let { hoge: Int ->
        println(hoge) // 10
        println(hoge * 2) // 20
        println(hoge * 4) // 40
    }
}