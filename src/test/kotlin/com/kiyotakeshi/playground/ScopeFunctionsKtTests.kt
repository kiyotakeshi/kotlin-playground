package com.kiyotakeshi.playground

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ScopeFunctionsKtTests {

    @Nested
    inner class LetTests {

        // let, run, with are transformation functions
        // return a target of another type
        @Test
        fun `let transformation function`() {
            val stringBuilder = StringBuilder("Hello, ")
            val result = stringBuilder.let {
                it.append("this is a transformation function.")
                it.append("It takes a StringBuilder instance and ")
                it.append("returns the number of characters in the generated String")
                it.length
            }
            assertThat(result).isEqualTo(135)
        }

        @Test
        fun `let transformation function explicit and change lambda argument`() {
            val stringBuilder = StringBuilder("Hello, ")
            // let は lambda の引数に it をとっているので、
            // 引数の変数名を変更することも、型を明示的に記載することもできる
            val result = stringBuilder.let { sb: StringBuilder ->
                sb.append("this is a transformation function.")
                sb.append("It takes a StringBuilder instance and ")
                sb.append("returns the number of characters in the generated String")
                sb.length
            }
            assertThat(result).isEqualTo(135)
        }

        @Test
        fun `let function`() {
            // let を使わなかった場合、新しい変数を定義し、使うたびにその名前を繰り返さないといけない
            val alice = Person("Alice", 20, "Amsterdam")
            println(alice)
            alice.moveTo("London")
            alice.incrementAge()
            println(alice)

            // > Scope functions don't introduce any new technical capabilities,
            // > but they can make your code more concise and readable.
            // スコープ関数はコードをより簡潔で読みやすいものにするための機能
            Person("Alice", 20, "Amsterdam")
                // receiver object を lambda の引数 it として参照する
                // public inline fun <T, R> T.let(block: (T) -> R): R {
                .let {
                    println(it)
                    it.moveTo("London")
                    it.incrementAge()
                    println(it)
                }
        }

        @Test
        fun `let method chain`() {
            val list = listOf("one", "two", "three")
            val result = list.map {
                it.uppercase()
            }.filter {
                it != "THREE"
            }
            assertThat(result).containsExactly("ONE", "TWO")

            // 変数に入れずに関数を使いたい場合にも使える
            list.map {
                it.uppercase()
            }.filter {
                it != "THREE"
            }.let { println(it) }

            // let を使わなかった場合...
            println(list.map {
                it.uppercase()
            }.filter {
                it != "THREE"
            })

            // let がコードブロックに引数として関数を一つだけ含む場合、 method reference も使用できる
            list.map {
                it.uppercase()
            }.filter {
                it != "THREE"
            // }.let(::println)
            }.let(::println)
        }

        @Test
        fun `map filter let`() {
            val numbers = mutableListOf(1, 2, 3, 4)
            val result = numbers
                .map { it * 2 }
                .filter { it > 5 }
                .let {
                    println(it) // [6, 8]
                    it.sum()
                }
            assertThat(result).isEqualTo(14)
        }

        @Test
        fun `map filter let2`() {
            val numbers = mutableListOf(1, 2, 3, 4)
            val result = numbers
                .filter { it > 4 }
                .let {
                    println(it) // []
                    it.sum()
                }
            assertThat(result).isEqualTo(0)
        }

        @Test
        fun `null let null`() {
            var name: String? = null

            // Compilation error. See log for more details
            // processNonNullString(name)
            // safe call operator `?.` と let を組み合わせることで、null の場合は実行されない
            val result: String? = name?.let {
                // this block will not be executed
                println(it)

                // non null なので実行できる
                // 関数の引数を基本的に non null で定義して ?.let で null でないことを確認して実行するべし
                processNonNullString(it)
                it.uppercase()
            }
            assertThat(result).isNull()
        }

        @Test
        fun `null let non null`() {
            var name: String? = null
            name = "mike"
            val result: String? = name?.let {
                println(it)
                // non null なので実行される
                // processNonNullString の実行結果は let の戻り値ではない
                processNonNullString(it)
                it.uppercase()
            }
            assertThat(result).isEqualTo("MIKE")
        }

        @Test
        fun `null let non null2`() {
            var name: String? = null
            name = "mike"
            val result: String? = name?.let {
                println(it)
                // let の終わりで実行しているので processNonNullString の実行結果が戻り値
                processNonNullString(it)
            }
            assertThat(result).isEqualTo("processing non null string: mike")
        }

        @Test
        fun `let with null and elvis operator`() {
            assertThat(getMessageWithLet(null)).isEqualTo("value was null")
            assertThat(getMessageWithLet("hello")).isEqualTo("value was not null: hello")
        }

        @Test
        fun `let with collection`() {
            val numbers = listOf("one", "two", "three", "four", "five")

            val result: Set<String> = numbers.associateWith { it.length } // Map<String, Int>
                .filterValues { it > 3 }
                .keys
            assertThat(result).containsExactly("three", "four", "five")

            // assertThat(result3).containsExactly("three", "four", "five")

            // もっと簡潔に書けた
            val result2 = numbers.filter { it.length > 3 }
            assertThat(result2).containsExactly("three", "four", "five")
        }
    }

    // also and apply are mutation functions
    // a mutation function operates on the given object and return it
    // apply is just like also, but with an implicit this
    // also と apply は与えられたオブジェクトを操作してそれを返す
    // apply は also と同じだが、暗黙的に this を使う
    @Nested
    inner class ApplyTests {
        @Test
        fun `apply function`() {
            val person = Person("Alice", 20, "Amsterdam")
                // receiver object として this を参照して、 receiver object を返す
                // Calls the specified function block with this value as its receiver and returns this value.
                // public inline fun <T> T.apply(block: T.() -> Unit): T {
                // .apply { -> // lambda の signature としては argument はない
                .apply {
                    println("in apply")
                    println(this)
                    // this.moveTo("London")
                    moveTo("London")
                    incrementAge()
                    println(this)
                }
            println("out of apply")
            println(person)
            assertThat(person).isEqualTo(Person("Alice", 21, "London"))
        }

        @Test
        fun `apply the following assignments to the object`() {

            // 値を返さずに主に receiver object のメンバを操作するコードブロックに使用する事が勧められている
            // use it for code blocks that don't return a value
            // and that mainly operate on the members of the receiver object.
            val adam = Person("adam").apply {
                age = 32
                city = "London"
            }
            assertThat(adam).isEqualTo(Person("adam", 32, "London"))
        }

        @Test
        fun `apply method chain`() {
            val list = mutableListOf("one", "two", "three")
            list.apply {
                add("four")
                remove("one")
            }.let { println(it) }
        }
    }

    @Nested
    inner class WithTests {
        @Test
        fun `with`() {
            val person = Person("Alice", 20, "Amsterdam")
            // Calls the specified function block with the given receiver as its receiver and returns its result.
            // public inline fun <T, R> with(receiver: T, block: T.() -> R): R {
            // receiver object は this として参照(=apply と同じ)
            // 拡張関数ではなく引数として receiver object を受け取る
            // lambda の結果を返す
            val result = with(person) {
                println(this)
                moveTo("London")
                incrementAge()
                this
            }
            assertThat(person).isEqualTo(Person("Alice", 21, "London"))
            assertThat(result).isEqualTo(Person("Alice", 21, "London"))
        }
    }

    @Nested
    inner class RunTests {
        // let, run, with are transformation functions
        // return a target of another type
        @Test
        fun `run`() {
            val person = Person("Alice", 20, "Amsterdam")

            // Calls the specified function block with this value as its receiver and returns its result.
            // public inline fun <T, R> T.run(block: T.() -> R): R {
            // receiver object は暗黙の(implicit) this として参照(=apply, with と同じ)
            // lambda の結果を返す(with と同じ)
            // apply の receiver object を返さないバージョン
            val result = person.run {
                println(this)
                moveTo("London")
                incrementAge()
                this
            }
            assertThat(result).isEqualTo(Person("Alice", 21, "London"))
        }

        @Test
        fun `run transformation function`() {
            val stringBuilder = StringBuilder("Hello, ")
            val result = stringBuilder.run {
                append("this is a transformation function.")
                append("It takes a StringBuilder instance and ")
                append("returns the number of characters in the generated String")
                length
            }
            assertThat(result).isEqualTo(135)
        }

        @Test
        fun `run with null and elvis operator`() {
            assertThat(getMessageWithRun(null)).isEqualTo("value was null")
            assertThat(getMessageWithRun("hello")).isEqualTo("value was not null: hello")
        }
    }

    // also and apply are mutation functions
    // a mutation function operates on the given object and return it
    // also と apply は与えられたオブジェクトを操作してそれを返す
    // "and also do the following with the object"(そして、そのオブジェクトで次のことも行う) という用途
    @Nested
    inner class AlsoTests {

        @Test
        fun `also`() {
            val restClient = RestClient("http://www.someurl.com")
            val logger = Logger()

            val headers = restClient.getResponse()
                // 呼び出されたオブジェクトを返すので
                // public inline fun <T> T.also(block: (T) -> Unit): T {
                // .also { it: Response ->
                .also { logger.info(it.toString()) } // Message is: Response(headers=Headers(headerInfo=some header info))
                // also の inline function を抜けた際に、 receiver object と同じ Response 型が返る
                .headers

            assertThat(logger.wasCalled()).isTrue()
            assertThat(headers.headerInfo).isEqualTo("some header info")
        }
    }

    @Nested
    inner class TakeTests {
        // takeIf and takeUnless let you embed checks of the object's state into the call chain
        // object の状態のチェックを call chain に埋め込む事ができる
        // 与えられた述語を満たす場合にオブジェクトを返し、満たさない場合は null を返す
        // つまり、単一のオブジェクトに対するフィルタリング関数
        @Test
        fun `takeIf null`() {
            val person = Person("Alice", 20, "Amsterdam")
            assertThat(person.takeIf { it.age > 21 }).isNull()
        }

        @Test
        fun `takeIf person`() {
            val person = Person("Alice", 20, "Amsterdam")
            assertThat(person.takeIf { it.age > 19 }).isEqualTo(person)
        }

        @Test
        fun `takeUnless person`() {
            val person = Person("Alice", 20, "Amsterdam")
            assertThat(person.takeUnless { it.age > 21 }).isEqualTo(person)
        }

        @Test
        fun `takeUnless null`() {
            val person = Person("Alice", 20, "Amsterdam")
            assertThat(person.takeUnless { it.age > 19 }).isNull()
        }

        @Test
        fun `takeIf and chaining function`() {
            val str = "hello"
            // safe call operator `?.` と組み合わせる
            assertThat(
                str.takeIf { it.isNotEmpty() }?.uppercase()
            ).isEqualTo("HELLO")
        }

        @Test
        fun `takeIfContainsW`() {
            assertThat(takeIfContainsW("hello")).isEqualTo("no 'w' in hello")
            assertThat(takeIfContainsW("world")).isEqualTo("world")
        }

        @Test
        fun `takeIf combination with scope function`() {
            // println("hello".indexOf("h")) // 0
            // println("hello".indexOf("w")) // -1
            val sub = "h"
            val input = "hello"

            input.indexOf(sub).takeIf { it >= 0 }?.let {
                println("the substring $sub is found in $it") // the substring h is found in 0
                println("its start position is $it") // its start position is 0
            }

            // takeIf は null を返すので、その後の処理は実行されない
            input.indexOf("w").takeIf { it >= 0 }?.let {
                println("not exec")
            }

            // takeIf を使わない場合
            if (input.indexOf(sub) >= 0) {
                println("the substring $sub is found in $") // the substring h is found in 0
            }

            if(input.indexOf("w") >= 0) {
                println("not exec")
            }
        }
    }
}
