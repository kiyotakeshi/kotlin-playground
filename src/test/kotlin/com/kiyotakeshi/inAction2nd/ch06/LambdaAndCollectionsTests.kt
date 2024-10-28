package com.kiyotakeshi.inAction2nd.ch06

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class LambdaAndCollectionsTests {

    data class Person(val name: String, val age: Int?)
    data class Book(val title: String, val authors: List<String>)

    @Test
    fun testFindTheOldest() {
        data class Person(val name: String, val age: Int)

        val people = listOf(Person("Alice", 29), Person("Bob", 31))

        // 以下は全て同じ動作
        // val oldestAgePerson = people.maxByOrNull({ p: Person -> p.age })
        // val oldestAgePerson = people.maxByOrNull() { p: Person -> p.age } // lambda を括弧の外に移動
        // val oldestAgePerson = people.maxByOrNull { p: Person -> p.age } // 空になった () を削除
        // val oldestAgePerson = people.maxByOrNull { p -> p.age } // parameter を型推論を使用
        // val oldestAgePerson = people.maxByOrNull { it.age } // 唯一の lambda の parameter に暗黙の名前を使用
        val oldestAgePerson = people.maxByOrNull(Person::age) // convert lambda to reference で member reference の形式で記述
        // member reference は function を値に変換するための :: 演算子を使用する
        // KPbroperty1<Person, Int>
        // val getAge = Person::age
        // Person -> Class, age -> Member

        assertThat(oldestAgePerson).isEqualTo(Person("Bob", 31))
    }

    @Test
    fun lawPerformanceFilter() {
        val people = (0..30_000).map {
            Person(
                "name$it",
                (0..100).random()
            )
        }
        val timeTaken = measureTimeMillis {
            people.filter {
                // filter の条件を中で組み立てると毎回実行され時間がかかるので注意
                val oldestPerson = people.maxBy { it.age ?: 0 }
                it.age == oldestPerson.age
            }
        }

        println("LawPerformanceFilter took $timeTaken ms") // LawPerformanceFilter took 1940 ms
    }

    @Test
    fun highPerformanceFilter() {
        val people = (0..50_0000).map {
            Person(
                "name$it",
                if (it % 10 == 0) null else (0..100).random()
            )
        }
        val timeTaken = measureTimeMillis {
            val maxAge = people.maxBy { it.age ?: 0 }
            people.filter {
                it.age == maxAge.age
            }
        }
        println("HighPerformanceFilter took $timeTaken ms") // HighPerformanceFilter took 10 ms
    }

    @Test
    fun partition() {
        val people = listOf(
            Person("Alice", 20),
            Person("Bob", 31),
            Person("Charlie", 30),
            Person("Dave", 31),
            Person("Mike", null),
            Person("Eve", 29),
        )
        val (evenAgedPeople, oddOrNullAgedPeople,) = people.partition {
            it.age?.let { age -> age % 2 == 0 } ?: false
        }

        println("Even aged people: $evenAgedPeople")
        println("Odd aged people: $oddOrNullAgedPeople")
    }

    @Test
    fun partitionFilterNull() {
        val people = listOf(
            Person("Alice", 20),
            Person("Bob", 31),
            Person("Charlie", 30),
            Person("Dave", 31),
            Person("Mike", null),
            Person("Eve", 29),
        )
        val (evenAgedPeople, oddAgedPeople) = people
            .filter { it.age != null }
            .partition { it.age?.rem(2) == 0 } // rem(2) は % 2 と同じ意味
        // .partition { it.age!! % 2 == 0 }
        println("Even aged people: $evenAgedPeople")
        println("Odd aged people: $oddAgedPeople")
    }

    @Test
    fun associate() {
        val people = listOf(
            Person("mike", 30),
            Person("kendrick", 31),
        )
        val nameToAge = people
            .associate {
                // key と value のペアを指定
                it.name to it.age
            }
        assertThat(nameToAge["mike"]).isEqualTo(30)
        assertThat(nameToAge["kendrick"]).isEqualTo(31)
    }

    @Test
    fun associateWith() {
        val people = listOf(
            Person("mike", 30),
            Person("kendrick", 31),
            Person("yamada", 30),
        )
        val personToAge = people
            // collection の元の要素を key として使用する
            .associateWith {
                it.age
            }
        assertThat(personToAge[Person("mike", 30)]).isEqualTo(30)
        assertThat(personToAge[Person("kendrick", 31)]).isEqualTo(31)
        assertThat(personToAge[Person("yamada", 30)]).isEqualTo(30)
    }

    @Test
    fun associateBy() {
        val people = listOf(
            Person("mike", 30),
            Person("kendrick", 31),
            Person("yamada", 30),
        )
        val ageToPerson = people
            // collection の元の要素を value として使用し、 lambda で key を指定
            .associateBy {
                it.age
            }
        // key が同じ値なので上書きされる
        assertThat(ageToPerson[30]?.name).isEqualTo("yamada")
        assertThat(ageToPerson[31]?.name).isEqualTo("kendrick")
    }

    @Test
    fun mapNotNullToMap() {
        val people = listOf(
            Person("mike", 30),
            Person("kendrick", 31),
            Person("yamada", null),
        )
        val ageToPerson: Map<Int, Person> = people
            .mapNotNull { person ->
                person.age?.let { age ->
                    age to person
                }
            } // Pair<Int, Person>
            .toMap()

        assertThat(ageToPerson).hasSize(2)
        assertThat(ageToPerson[30]?.name).isEqualTo("mike")
        assertThat(ageToPerson[31]?.name).isEqualTo("kendrick")
    }

    @Test
    fun ifEmptyAndIfBlank() {
        val empty = emptyList<String>()
        val full = listOf("apple", "banana", "cherry")
        assertThat(empty.ifEmpty { listOf("no", "values", "here") }).containsExactly("no", "values", "here")
        assertThat(full.ifEmpty { listOf("no", "values", "here") }).containsExactly("apple", "banana", "cherry")

        val replaceBlank = listOf("apple", "banana", "").map {
            it.ifBlank { "blank" }
        }
        assertThat(replaceBlank).containsExactly("apple", "banana", "blank")
    }

    @Test
    fun windowed() {
        // 温度センサーの毎日の測定値のリスト
        val temperatures = listOf(27.7, 29.8, 22.0, 35.5, 19.1)

        //  各日セットの 3 日間の平均を取得するために sliding window を作成する
        println(temperatures.windowed(3))
        // [[27.7, 29.8, 22.0], [29.8, 22.0, 35.5], [22.0, 35.5, 19.1]]

        println(temperatures.windowed(3) { it.sum() / it.size })
        // [26.5, 29.099999999999998, 25.53333333333333]

        // sliding window ではなく、 chunk に分割する
        println(temperatures.chunked(2))
        // [[27.7, 29.8], [22.0, 35.5], [19.1]]
    }

    @Test
    fun zip() {
        val names = listOf("Alice", "Bob", "Charlie")
        val ages = listOf(29, 31, 30)
        val nameAgePairList = names.zip(ages)
        println("nameAgePairList: $nameAgePairList")
        // nameAgePairList: [(Alice, 29), (Bob, 31), (Charlie, 30)]

        val nameToAge = names.zip(ages).toMap()
        assertThat(nameToAge["Alice"]).isEqualTo(29)
        assertThat(nameToAge["Bob"]).isEqualTo(31)
        assertThat(nameToAge["Charlie"]).isEqualTo(30)
    }

    @Test
    fun flatMap() {
        val books = listOf(
            Book("Thursday Next", listOf("Jasper Fforde")),
            Book("Mort", listOf("Terry Pratchett")),
            Book("Good Omens", listOf("Terry Pratchett", "Neil Gaiman"))
        )
        val wronfAuthors: List<List<String>> = books.map { it.authors }
        println("wronfAuthors: $wronfAuthors")
        // nest された collection のまま...
        // wronfAuthors: [[Jasper Fforde], [Terry Pratchett], [Terry Pratchett, Neil Gaiman]]

        val authors = books.flatMap { it.authors }.toSet()
        println("authors: $authors")
        // authors: [Jasper Fforde, Terry Pratchett, Neil Gaiman]
    }

    /**
     * sequence を使った遅延実行の方が実行時間が長くなった...
     * 計測することが大事で、データ量が増えたら必ず sequence を使うというわけではない
     */
    @Test
    fun highPerformanceSequenceFilter() {
        val people = (0..50_00000).map {
            Person(
                "name$it",
                if (it % 10 == 0) null else (0..100).random()
            )
        }
        val filterTimeTaken = measureTimeMillis {
            people.map(Person::name)
                .filter { it.contains("9") }
        }
        println("PerformanceFilter took $filterTimeTaken ms") // PerformanceFilter took 87 ms

        val filterSequenceTimeTaken = measureTimeMillis {
            people
                .asSequence()
                .map(Person::name)
                .filter { it.contains("9") } // : Sequence<String>
                .toList()
        }
        println("PerformanceSequenceFilter took $filterSequenceTimeTaken ms") // PerformanceSequenceFilter took 172 ms
    }
}
