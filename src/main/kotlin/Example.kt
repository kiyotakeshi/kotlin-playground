fun printText() {
    val text = "hello kotlin.";
    println(text)
}

class Human {
    fun showName(name: String){
        println(name)
    }
}

fun main() {
    printText();
    val human = Human()
    human.showName("mike")

    val immutableList: List<Int> = listOf(1, 2, 3)
    // immutableList.add(3)

    // 基本的には型推論を使う
    // val mutableList: MutableList<Int> = mutableListOf(1, 2, 3)
    val mutableList = mutableListOf(1, 2, 3)
    mutableList.add(4)
    println(mutableList) // [1, 2, 3, 4]

    // val mutableMap: MutableMap<Int, String> = mutableMapOf(1 to "one", 2 to "two", 3 to "three")
    val mutableMap = mutableMapOf(1 to "one", 2 to "two", 3 to "three")
    println(mutableMap.containsKey(3)) // true
    mutableMap[4] = "four"
    println(mutableMap) // {1=one, 2=two, 3=three, 4=four}
}
