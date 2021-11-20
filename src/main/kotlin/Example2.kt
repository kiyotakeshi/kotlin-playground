// if, when 文を式として扱える
fun printOddOrEvenNumberText(num: Int) {

    // if 文を式として扱うことで、 if 文が結果の値を返す
    // それを変数に代入する(JavaScript 的な)
    // text は val で定義したので書き換えの可能性も減らせる
//    val text = if (num % 2 == 1) {
//        "Odd"
//    } else {
//        "Even"
//    }
    val text = if (num % 2 == 1) "Odd" else "Even"
    println(text)
}

fun getOddOrEvenNumberText(num: Int): String {
    return if (num % 2 == 1) "Odd" else "Even"
}


class User {
    // var だと getter,setter が自動で生成される
    var name = ""
}

class User2(id: Int) {
    // val には setter は作られない
    val id = id
    var name = ""
}

class User3 {
    // 初期値を設定せず定義、処理の中で必ず値を入れたい場合に使用
    // あとから値を書き換えるため var のみで使える
    lateinit var name: String
}

class User4 {
    lateinit var name: String

    // プロパティに対する get() 関数の処理を書き換え(拡張プロパティ)
    val isValidName: Boolean get() = name != ""
}

// accessor, equals, hashCode, toString, componentN, copy が生成される
data class User5(val id: Int, var name: String)

fun printUserInfo(id: Int, name: String = "default"){
    println("id = $id name=${name}")
}

data class User6(val id:Int, val name: String = "Defalut")

fun main() {
    printOddOrEvenNumberText(1)
    printOddOrEvenNumberText(2)
    println(getOddOrEvenNumberText(2));

    val user = User()
    // lateinit にしていないので値が格納されていなくても呼び出せる、空文字列 ""
    println("username: ${user.name}") // username:

    // setter を経由して格納されている
    user.name = "Mike"
    println(user.name)

    val user2 = User2(1)
    user2.name = "Popcorn"
    println("${user2.id}: ${user2.name}")

    val user3 = User3()
    // 値が格納されていない状態で呼び出すとエラー
    // Exception in thread "main" kotlin.UninitializedPropertyAccessException: lateinit property name has not been initialized
    // println(user3.name)

    val user4 = User4()
    // Exception in thread "main" kotlin.UninitializedPropertyAccessException: lateinit property name has not been initialized
    // println(user4.isValidName)
    user4.name = "taro"
    println(user4.isValidName) // true

    // データクラスはコンストラクタの定義が必須
    val user5 = User5(1, "kendrick")
    user5.name = "kendrick lamar"
    val sameUser5 = User5(1, "kendrick lamar")
    val otherUser5 = User5(2, "kendrick lamar")
    println(user5) // User5(id=1, name=kendrick lamar)
    println(user5 == sameUser5) // true
    println(user5 == otherUser5) // false
    // 順番を指定してプロパティの値を取得できる
    println(user5.component1()) // 1
    println(user5.component2()) // kendrick lamar

    // デフォルト引数を使用
    printUserInfo(1)

    // インスタンスの生成でもデフォルト引数は使用可能
    val user6 = User6(1)
    println(user6) // User6(id=1, name=Defalut)
}

