// 関数を値として記述する = 関数リテラル

// ラムダ式
// 変数 calc にInt型の引数を2つ受け取り、Int型の戻り値を返すという関数型を定義
// val calc: (Int, Int) -> Int = { num1: Int, num2: Int ->
// 型推論が使える
val calc: (Int, Int) -> Int = { num1, num2 ->
    num1 + num2
}

// 引数が1つだと引数の名前も省略可能(it という名前が使われる)
val squared: (Int) -> Int = { it * it }

// 無名関数
// 関数定義から名前の部分だけを消したような形で記述
// 戻り地の方を明示的に記述する必要がある場合はラムダ式ではなくこちらを使う
val squared2: (Int) -> Int = fun(num: Int): Int { return num * num }

// 高階関数
// 関数型のオブジェクトを引数に受け取れる関数のこと
typealias Calc = (Int, Int) -> Int

// fun printCalcResult(num1: Int, num2: Int, calc: (Int, Int) -> Int) {
fun printCalcResult(num1: Int, num2: Int, calc: Calc) {
    val result = calc(num1, num2)
    println(result)
}

// with はあるオブジェクトに対して複数の処理を行うときに使用
// 第1引数(mutableListOf) がレシーバとなるオブジェクト
// 第2引数(コードブロック箇所)がレシーバのオブジェクトを処理して任意の型を返す関数
val oddLists = with(mutableListOf<Int>()) {
    for (i in 1..10) {
        // レシーバの this は省略可能
        // if (i % 2 == 1) this.add(i)
        if (i % 2 == 1) add(i)
    }
    // this.joinToString(separator = " ")
    joinToString(separator = " ")
}


fun main() {
    println(calc(1, 2))
    println(squared(2))
    println(squared2(3))
    printCalcResult(10, 20) { num1, num2 ->
        num1 + num2
    }
    printCalcResult(10, 20) { num1, num2 ->
        num1 * num2
    }
    println(oddLists)
}