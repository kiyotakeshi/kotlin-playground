package com.kiyotakeshi.playground

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RecipesTests {

    private val sut: Recipes = Recipes()

    @Test
    fun v1() {
        sut.getRecommendationV1( "sugar", 30).let { filteredRecipes ->
            assertThat(filteredRecipes[0].name).isEqualTo("Chocolate Chip Cookies")
            assertThat(filteredRecipes[1].name).isEqualTo("Churros")
            assertThat(filteredRecipes[2].name).isEqualTo("Pancakes")
        }
    }

    @Test
    fun v2() {
        sut.getRecommendationV2("sugar", 30).let { filteredRecipes ->
            assertThat(filteredRecipes[0].name).isEqualTo("Chocolate Chip Cookies")
            assertThat(filteredRecipes[1].name).isEqualTo("Churros")
            assertThat(filteredRecipes[2].name).isEqualTo("Pancakes")
        }
    }
}
