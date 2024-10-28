package com.kiyotakeshi.inAction2nd.ch10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HighOrderFunctionTests {
    @Suppress("ExplicitItLambdaParameter")
    @Test
    fun highOrderFunctionArgumentLambda() {
        val letters = listOf("mike", "kendrick", "jackson")
        assertThat(letters.joinToString()).isEqualTo("mike, kendrick, jackson")
        assertThat(letters.joinToString(transform = { it.uppercase() })).isEqualTo("MIKE, KENDRICK, JACKSON")
        assertThat(letters.joinToString { it: String -> it.uppercase() }).isEqualTo("MIKE, KENDRICK, JACKSON")
    }

    @Test
    fun highOrderFunctionReturnLambda() {
        val expeditedShippingCostCalculator: (Order) -> Double = getShippingCostCalculator(Delivery.EXPEDITED)
        assertThat(expeditedShippingCostCalculator(Order(3))).isEqualTo(16.0)

        val standardShippingCostCalculator = getShippingCostCalculator(Delivery.STANDARD)
        assertThat(standardShippingCostCalculator(Order(4))).isEqualTo(5.6)
    }

    @Test
    fun predicate() {
        val contacts = listOf(
            Person("mike", "west", "123-4567"),
            Person("kendric", "jackson", "234-5678")
        )
        val filters = ContactListFilters().apply {
            prefix = "m"
            onlyWithPhoneNumber = true
        }
        val filterdPerson = contacts.filter(filters.getPredicate())
        assertThat(filterdPerson).isEqualTo(listOf(Person("mike", "west", "123-4567")))
    }

    @Test
    fun labelReturn() {
        val people = listOf(
            Person("jackson", "west"),
            Person("kendric", "mike"),
            Person("bob", "tom")
        )
        val bobFirst = listOf(
            Person("bob", "tom"),
            Person("kendric", "mike"),
        )
        println("lookForBob(people)")
        lookForBob(people)

        println("lookForBob(bobFirst)")
        lookForBob(bobFirst)

        println("lookForBobWithLabel(people)")
        lookForBobWithLabel(people)
    }
}
