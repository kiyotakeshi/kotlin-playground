package com.kiyotakeshi.collections

import com.kiyotakeshi.classes.Person
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.Comparator

class CollectionOperationsKtTests {

    @Nested
    inner class MapTests {
        @Test
        fun map() {
            val numbers = listOf(1, 2, 3, 4)
            val doubled = numbers.map { it * 2 }
            // val expected = listOf(2, 4, 6, 8)
            // assertThat(doubled).isEqualTo(expected)
            assertThat(doubled).containsExactly(2, 4, 6, 8)
        }

        @Test
        fun mapIndexed() {
            val numbers = listOf(1, 2, 3, 4)

            // .mapIndexed {} は inline function
            // public inline fun <T, R> Iterable<T>.mapIndexed(transform: (index: Int, T) -> R): List<R> {
            //    return mapIndexedTo(ArrayList<R>(collectionSizeOrDefault(10)), transform)
            //}
            val squared = numbers.mapIndexed { index, value -> value * index }
            assertThat(squared).containsExactly(0, 2, 6, 12)
        }

        @Test
        fun mapNotNull() {
            val numbers = listOf(1, 2, 3, 4, 6)

            // 2 は null になるので、それを除いた結果が返る
            val squared = numbers.mapNotNull { if (it == 2) null else it * 2 }
            assertThat(squared).containsExactly(2, 6, 8, 12)
        }

        @Test
        fun mapIndexedNotNull() {
            val numbers = listOf(1, 2, 3, 4)
            val squared = numbers.mapIndexedNotNull { index, value -> if (index == 1) null else value * index }
            assertThat(squared).containsExactly(0, 6, 12)
        }
    }

    @Nested
    inner class ZipTests {
        @Test
        fun zip() {
            val colors = listOf("red", "green", "blue")
            val animals = listOf("fox", "bear", "wolf")
            // infix function
            // public infix fun <T, R> Iterable<T>.zip(other: Iterable<R>): List<Pair<T, R>> {
            //    return zip(other) { t1, t2 -> t1 to t2 }
            //}
            // val zippedList = colors zip animals
            val zippedList: List<Pair<String, String>> = colors.zip(animals)
            println(zippedList) // [(red, fox), (green, bear), (blue, wolf)]

            // val pair: Pair<String, String> = "red".to("fox")
            val pair: Pair<String, String> = "red" to "fox"
            val pair2 = Pair("green", "bear")
            assertThat(zippedList).containsExactly(pair, pair2, "blue" to "wolf")
        }

        @Test
        fun zipTwoParameter() {
            val colors = listOf("red", "green", "blue")
            val animals = listOf("fox", "bear", "wolf")
            val zippedList: List<String> =
                colors.zip(animals) { color, animal -> "${animal.replaceFirstChar { it.uppercase() }} is $color" }
            assertThat(zippedList).containsExactly("Fox is red", "Bear is green", "Wolf is blue")
        }
    }

    @Nested
    inner class AssociateTests {
        @Test
        fun associateWith() {
            val numbers = listOf("one", "two", "three", "four")
            // associateWith は指定した key で Map に変換する
            val associations: Map<String, Int> = numbers.associateWith { it.length }
            println(associations) // {one=3, two=3, three=5, four=4}
            assertThat(associations).containsKeys("one", "two", "three", "four")
            assertThat(associations).containsValues(3, 3, 5, 4)

            val pairs: Pair<String, Int> = "one" to 3
            val expected = mapOf(pairs, "two" to 3, "three" to 5, "four" to 4)
            assertThat(associations).containsExactlyEntriesOf(expected)
        }

        @Test
        fun associateBy() {
            val numbers = listOf("one", "two", "three", "four")
            val associateBy = numbers.associateBy { it.first().uppercaseChar() }
            println(associateBy) // {O=one, T=three, F=four}

            val associateBy2 = numbers.associateBy(
                keySelector = { it.first().uppercaseChar() },
                valueTransform = { it.length })
            println(associateBy2) // {O=3, T=5, F=4}
            assertThat(associateBy2).containsExactlyEntriesOf(mapOf('O' to 3, 'T' to 5, 'F' to 4))
        }
    }

    @Nested
    inner class FlattenTests {
        @Test
        fun flatten() {
            val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6))
            println(numberSets) // [[1, 2, 3], [4, 5, 6]]
            val flattenedSet = numberSets.flatten()
            assertThat(flattenedSet).containsExactly(1, 2, 3, 4, 5, 6)
        }

        @Test
        fun flatMap() {
            val numbers = listOf(1, 2, 3, 4)
            // Collection の各要素に対して指定された変更関数を適用し、その結果として得られた Collection を一つにまとめて平坦化する
            val processedNumbers: List<Int> = numbers.flatMap { number -> listOf(number, number * number) }
            assertThat(processedNumbers).containsExactly(1, 1, 2, 4, 3, 9, 4, 16)
        }
    }

    @Nested
    inner class FilterTests {
        @Test
        fun filter() {
            val numbers = listOf("one", "two", "three", "four")
            val longerThan3 = numbers.filter { it.length > 3 }
            assertThat(longerThan3).containsExactly("three", "four")
        }

        @Test
        fun filterNot() {
            val numbers = listOf("one", "two", "three", "four")
            val notContainingT = numbers.filterNot { it.contains("t") }
            assertThat(notContainingT).containsExactly("one", "four")
        }

        @Test
        fun filterIndexed() {
            val numbers = listOf("one", "two", "three", "four")
            val filtered = numbers.filterIndexed { index, s -> index != 1 && s.length < 5 }
            assertThat(filtered).containsExactly("one", "four")
        }

        @Test
        fun filerIsInstance() {
            val mixed = listOf("one", 2, "three", 4)
            val strings = mixed.filterIsInstance<String>()
            assertThat(strings).containsExactly("one", "three")
        }

        @Test
        fun filterNotNull() {
            val listWithNulls: List<String?> = listOf("one", null, "two", null, "three")
            val nonNulls = listWithNulls.filterNotNull()
            assertThat(nonNulls).containsExactly("one", "two", "three")
        }

        @Test
        fun partition() {
            val numbers = listOf(1, 2, 3, 4)
            val (even, odd) = numbers.partition { it % 2 == 0 }
            assertThat(even).containsExactly(2, 4)
            assertThat(odd).containsExactly(1, 3)
        }

        @Test
        fun any() {
            val numbers = listOf(1, 2, 3, 4)
            // Collection の要素のいずれかが条件を満たすかどうかを判定
            assertThat(numbers.any { it % 2 == 0 }).isTrue()
        }

        @Test
        fun all() {
            val numbers = listOf(1, 2, 3, 4)
            assertThat(numbers.all { it % 2 == 0 }).isFalse()
        }

        @Test
        fun none() {
            val numbers = listOf(1, 2, 3, 4)
            // 全て合致していない
            assertThat(numbers.none() { it < 0 }).isTrue()
        }
    }

    @Nested
    inner class PlusMinusTests {
        @Test
        fun plus() {
            val numbers = listOf(1, 2, 3)
            // 新しい list を生成する
            val plusList: List<Int> = numbers + 4
            assertThat(plusList).containsExactly(1, 2, 3, 4)
        }

        @Test
        fun plus2() {
            val numbers = listOf(1, 2, 3)
            val numbers2 = listOf(4, 5, 6)
            val plusList = numbers + numbers2
            assertThat(plusList).containsExactly(1, 2, 3, 4, 5, 6)
        }

        @Test
        fun minus() {
            val numbers = listOf(1, 2, 3)
            val minusList = numbers - 2
            assertThat(minusList).containsExactly(1, 3)
        }

        @Test
        fun minus2() {
            val numbers = setOf(1, 2, 3, 4, 5, 3, 5)
            val numbers2 = listOf(3, 5)
            val minusList: Set<Int> = numbers - numbers2
            assertThat(minusList).containsExactly(1, 2, 4)
        }
    }

    @Nested
    inner class GroupTests {
        @Test
        fun groupBy() {
            val numbers = listOf("one", "two", "three", "four", "five")
            val groupedBy: Map<Char, List<String>> = numbers.groupBy { it.first().uppercaseChar() }
            println(groupedBy) // {O=[one], T=[two, three], F=[four, five]}

            val expected = mapOf('O' to listOf("one"), 'T' to listOf("two", "three"), 'F' to listOf("four", "five"))
            assertThat(groupedBy).containsExactlyEntriesOf(expected)
        }

        @Test
        fun groupBy2() {
            val numbers = listOf("one", "two", "three", "four", "five")
            val groupedBy2 = numbers.groupBy(
                keySelector = { it.first().uppercaseChar() },
                valueTransform = { it.length })
            println(groupedBy2) // {O=[3], T=[3, 5], F=[4, 4]}
            val expected = mapOf('O' to listOf(3), 'T' to listOf(3, 5), 'F' to listOf(4, 4))
            assertThat(groupedBy2).containsExactlyEntriesOf(expected)
        }

        @Test
        fun groupingByEachCount() {
            // 遅延評価(lazy evaluation)を行い、終端処理で map を返す
            val words = listOf("one", "two", "three", "four", "five")
            val grouping: Grouping<String, Char> = words.groupingBy { it.first().uppercaseChar() }
            println(grouping) // com.kiyotakeshi.collections.CollectionOperationsKtTests$GroupTests$groupingBy$$inlined$groupingBy$1@17bffc17

            // 終端関数(terminal operation)で初めて grouping 化
            val eachCount = grouping.eachCount()
            println(eachCount) // {O=1, T=2, F=2}
            assertThat(eachCount).containsExactlyEntriesOf(mapOf('O' to 1, 'T' to 2, 'F' to 2))
        }

        @Test
        fun groupingByReduce() {
            val words = listOf("one", "two", "three", "four", "five", "ten")
            val reduce = words.groupingBy { it.first() }
                // 単一の値に集約する
                .reduce { _, accumulator, element -> accumulator + element }

            val expected = mapOf('o' to "one", 't' to "twothreeten", 'f' to "fourfive")
            assertThat(reduce).containsExactlyEntriesOf(expected)
        }

        @Test
        fun groupingByFold() {
            val numbers = listOf(1, 2, 3, 4, 5, 6)
            // val eachCount: Map<Int, Int> = numbers.groupingBy { it % 2 }.eachCount()
            // println(eachCount) // {1=3, 0=3}
            val folded = numbers.groupingBy { it % 2 }
                .fold(10) { accumulator, element -> accumulator + element }
            assertThat(folded).containsExactlyEntriesOf(mapOf(1 to 19, 0 to 22))
        }
    }

    data class Person(val name: String, val age: Int?)

    @Nested
    inner class SingleElementTest {
        @Test
        fun first() {
            val numbers = listOf(1, 2, 3, 4)
            assertThat(numbers.first()).isEqualTo(1)

            assertThat(numbers.first { it % 2 == 0 }).isEqualTo(2)
        }

        @Test
        fun firstOrNull() {
            val numbers = listOf<Int>()
            assertThat(numbers.firstOrNull()).isNull()

            val numbers2 = listOf(1, 2, 3, 4)
            // 条件を満たさない場合も null が返る
            assertThat(numbers2.firstOrNull { it % 5 == 0 }).isNull()
        }

        @Test
        fun firstNotNullOf() {
            val people = listOf(
                Person("mike", null),
                Person("john", 29),
            )

            // 最初の non null の値を返す
            // ヒットしないと NoSuchElementException が発生する
            assertThat(people.firstNotNullOf { it.age }).isEqualTo(29)
        }

        @Test
        fun firstNotNullOfNull() {
            val people = listOf(
                Person("mike", null),
                Person("john", null)
            )

            assertThat(people.firstNotNullOfOrNull { it.age }).isNull()
        }

        @Test
        fun elementAt() {
            val numbers = listOf(1, 2, 3, 4)
            assertThat(numbers.elementAt(2)).isEqualTo(3)
            assertThat(numbers.elementAtOrElse(4) { "not found"}).isEqualTo("not found")
        }
    }

    @Nested
    inner class SortTests {
        @Test
        fun sorted() {
            val numbers = listOf(3, 2, 1, 4)
            // 昇順(ascending order)
            val sortedNumbers = numbers.sorted()
            assertThat(sortedNumbers).containsExactly(1, 2, 3, 4)
        }

        @Test
        fun sortedDescending() {
            val numbers = listOf(3, 2, 1, 4)
            // 降順(descending order)
            val sortedNumbers = numbers.sortedDescending()
            assertThat(sortedNumbers).containsExactly(4, 3, 2, 1)
        }

        @Test
        fun sortedBy() {
            val people = listOf(
                Person("mike", 29),
                Person("john", 33),
                Person("sarah", 25)
            )
            val sortedByAge = people.sortedBy { it.age }
            assertThat(sortedByAge).containsExactly(
                Person("sarah", 25),
                Person("mike", 29),
                Person("john", 33)
            )
        }

        @Test
        fun sortedWith() {
            val people = listOf(
                Person("mike", 29),
                Person("john", 33),
                Person("sarah", 25),
                Person("mike", 20),

            )
            val comparetor: Comparator<Person> = compareBy<Person> { it.name }.thenBy { it.age }
            val sortedPeople = people.sortedWith(comparetor)
            assertThat(sortedPeople).containsExactly(
                Person("john", 33),
                Person("mike", 20),
                Person("mike", 29),
                Person("sarah", 25)
            )
        }

        @Test
        fun reserved() {
            val numbers = listOf(1, 2, 3, 4)
            // original の Collection は変更しない
            val reversedNumbers = numbers.reversed()
            assertThat(reversedNumbers).containsExactly(4, 3, 2, 1)
            assertThat(numbers).containsExactly(1, 2, 3, 4)
        }

        @Test
        fun shuffled() {
            val numbers = listOf(1, 2, 3, 4)
            val shuffledNumbers = numbers.shuffled()
            println(shuffledNumbers)
            // 元のリストは変更されない
            assertThat(numbers).containsExactly(1, 2, 3, 4)
        }
    }

    @Nested
    inner class AggregateTests {
        @Test
        fun maxOrNull() {
            val numbers = listOf(1, 2, 3, 4)
            assertThat(numbers.maxOrNull()).isEqualTo(4)

            val emptyList = listOf<Int>()
            assertThat(emptyList.maxOrNull()).isNull()
        }

        @Test
        fun maxByOrNull() {
            val people = listOf(
                Person("mike", 29),
                Person("john", null),
                Person("sarah", 25)
            )
            val oldestPerson = people.maxByOrNull { it.age ?: 0 }
            assertThat(oldestPerson).isEqualTo(Person("mike", 29))
        }

        @Test
        fun sumOf() {
            val people = listOf(
                Person("mike", 29),
                Person("john", 33),
                Person("sarah", null)
            )
            val sumOfAges = people.sumOf { it.age ?: 0 }
            assertThat(sumOfAges).isEqualTo(62)
        }

        @Test
        fun reduce() {
            val numbers = listOf(1, 2, 3, 4)
            val sum = numbers.reduce { acc, number -> acc + number }
            assertThat(sum).isEqualTo(10)
        }


        @Test
        fun fold() {
            val numbers = listOf(1, 2, 3, 4)
            // reduce と違い初期値がある
            val sum = numbers.fold(10) { sum, element -> sum + element }
            assertThat(sum).isEqualTo(20)
        }

        @Test
        fun foldIndexed() {
            val numbers = listOf(1, 2, 3, 4, 6)
            val sum = numbers.foldIndexed(10) { index, sum, element -> if(index % 2 == 0) sum + element else sum }
            assertThat(sum).isEqualTo(20)
        }
    }
}