package com.kiyotakeshi.inAction2nd.ch05

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class LambdaAndCollectionsTests {

    data class Person(val name: String, val age: Int?)

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
                val oldestPerson = people.maxBy{ it.age ?: 0 }
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
        val (evenAgedPeople, oddOrNullAgedPeople, ) = people.partition {
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
}
