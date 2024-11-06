package com.kiyotakeshi.playground

import java.util.function.Predicate

@Suppress("MaxLineLength", "MagicNumber")
data class Recipes(
    private val recipes: List<Recipe> = listOf(
        Recipe("Chicken Curry", 60, listOf(Ingredient("chicken"), Ingredient("potato"), Ingredient("onion"), Ingredient("carrot"), Ingredient("curry roux")), 5),
        Recipe("Omelette Rice", 30, listOf(Ingredient("chicken"), Ingredient("onion"), Ingredient("ketchup"), Ingredient("rice")), 4),
        Recipe("Oyakodon", 30, listOf(Ingredient("chicken"), Ingredient("onion"), Ingredient("egg"), Ingredient("soy sauce"), Ingredient("mirin")), 4),
        Recipe("Almond Jelly", 120, listOf(Ingredient("milk"), Ingredient("sugar"), Ingredient("almond flour"), Ingredient("gelatin")), 3),
        Recipe("Spaghetti Bolognese", 45, listOf(Ingredient("spaghetti"), Ingredient("ground beef"), Ingredient("onion"), Ingredient("tomato sauce")), 5),
        Recipe("Caesar Salad", 15, listOf(Ingredient("lettuce"), Ingredient("croutons"), Ingredient("parmesan cheese"), Ingredient("Caesar dressing")), 4),
        Recipe("Pancakes", 20, listOf(Ingredient("flour"), Ingredient("milk"), Ingredient("egg"), Ingredient("sugar"), Ingredient("baking powder")), 4),
        Recipe("French Toast", 15, listOf(Ingredient("bread"), Ingredient("milk"), Ingredient("egg"), Ingredient("sugar")), 3),
        Recipe("Miso Soup", 10, listOf(Ingredient("miso paste"), Ingredient("tofu"), Ingredient("seaweed"), Ingredient("green onion")), 4),
        Recipe("Grilled Cheese Sandwich", 10, listOf(Ingredient("bread"), Ingredient("cheese"), Ingredient("butter")), 3),
        Recipe("Chocolate Cake", 90, listOf(Ingredient("flour"), Ingredient("cocoa powder"), Ingredient("sugar"), Ingredient("egg"), Ingredient("butter")), 5),
        Recipe("Stir-Fried Vegetables", 20, listOf(Ingredient("broccoli"), Ingredient("carrot"), Ingredient("bell pepper"), Ingredient("soy sauce")), 4),
        Recipe("Tacos", 30, listOf(Ingredient("tortilla"), Ingredient("ground beef"), Ingredient("lettuce"), Ingredient("cheese")), 4),
        Recipe("Garlic Bread", 15, listOf(Ingredient("bread"), Ingredient("garlic"), Ingredient("butter")), 4),
        Recipe("Apple Pie", 60, listOf(Ingredient("apple"), Ingredient("flour"), Ingredient("sugar"), Ingredient("butter")), 5),
        Recipe("Tomato Soup", 30, listOf(Ingredient("tomato"), Ingredient("onion"), Ingredient("garlic"), Ingredient("basil")), 4),
        Recipe("Scrambled Eggs", 5, listOf(Ingredient("egg"), Ingredient("milk"), Ingredient("butter")), 3),
        Recipe("Beef Stew", 120, listOf(Ingredient("beef"), Ingredient("potato"), Ingredient("carrot"), Ingredient("onion")), 5),
        Recipe("Fried Rice", 15, listOf(Ingredient("rice"), Ingredient("egg"), Ingredient("soy sauce"), Ingredient("green peas")), 4),
        Recipe("Lasagna", 90, listOf(Ingredient("lasagna noodles"), Ingredient("ground beef"), Ingredient("tomato sauce"), Ingredient("cheese")), 5),
        Recipe("Pizza", 60, listOf(Ingredient("pizza dough"), Ingredient("tomato sauce"), Ingredient("cheese"), Ingredient("pepperoni")), 4),
        Recipe("Chocolate Chip Cookies", 25, listOf(Ingredient("flour"), Ingredient("butter"), Ingredient("sugar"), Ingredient("chocolate chips")), 5),
        Recipe("Fruit Salad", 10, listOf(Ingredient("apple"), Ingredient("banana"), Ingredient("orange"), Ingredient("grape")), 3),
        Recipe("Beef Tacos", 30, listOf(Ingredient("tortilla"), Ingredient("beef"), Ingredient("lettuce"), Ingredient("cheese")), 4),
        Recipe("Chicken Alfredo", 30, listOf(Ingredient("chicken"), Ingredient("pasta"), Ingredient("alfredo sauce"), Ingredient("parmesan cheese")), 5),
        Recipe("Pasta Salad", 20, listOf(Ingredient("pasta"), Ingredient("tomato"), Ingredient("cucumber"), Ingredient("olive oil")), 3),
        Recipe("Mango Smoothie", 5, listOf(Ingredient("mango"), Ingredient("yogurt"), Ingredient("milk")), 4),
        Recipe("Banana Bread", 60, listOf(Ingredient("banana"), Ingredient("flour"), Ingredient("sugar"), Ingredient("butter")), 5),
        Recipe("Shrimp Cocktail", 10, listOf(Ingredient("shrimp"), Ingredient("cocktail sauce")), 4),
        Recipe("Lemonade", 5, listOf(Ingredient("lemon"), Ingredient("sugar"), Ingredient("water")), 3),
        Recipe("Cheeseburger", 20, listOf(Ingredient("ground beef"), Ingredient("bun"), Ingredient("cheese"), Ingredient("lettuce")), 4),
        Recipe("Roast Chicken", 90, listOf(Ingredient("chicken"), Ingredient("butter"), Ingredient("garlic"), Ingredient("rosemary")), 5),
        Recipe("Spring Rolls", 30, listOf(Ingredient("rice paper"), Ingredient("lettuce"), Ingredient("shrimp"), Ingredient("rice noodles")), 4),
        Recipe("Sushi", 60, listOf(Ingredient("rice"), Ingredient("vinegar"), Ingredient("seaweed"), Ingredient("fish")), 5),
        Recipe("Quiche", 45, listOf(Ingredient("egg"), Ingredient("milk"), Ingredient("cheese"), Ingredient("pastry crust")), 4),
        Recipe("Avocado Toast", 5, listOf(Ingredient("bread"), Ingredient("avocado"), Ingredient("salt")), 3),
        Recipe("BBQ Ribs", 120, listOf(Ingredient("ribs"), Ingredient("bbq sauce")), 5),
        Recipe("Vegetable Soup", 40, listOf(Ingredient("carrot"), Ingredient("celery"), Ingredient("potato"), Ingredient("tomato")), 4),
        Recipe("Falafel", 20, listOf(Ingredient("chickpeas"), Ingredient("onion"), Ingredient("garlic"), Ingredient("parsley")), 4),
        Recipe("Clam Chowder", 45, listOf(Ingredient("clam"), Ingredient("potato"), Ingredient("celery"), Ingredient("cream")), 5),
        Recipe("Macaroni and Cheese", 20, listOf(Ingredient("macaroni"), Ingredient("cheese"), Ingredient("milk")), 4),
        Recipe("Eggplant Parmesan", 40, listOf(Ingredient("eggplant"), Ingredient("tomato sauce"), Ingredient("cheese")), 4),
        Recipe("Stuffed Peppers", 60, listOf(Ingredient("bell pepper"), Ingredient("rice"), Ingredient("ground beef"), Ingredient("tomato sauce")), 5),
        Recipe("Ratatouille", 50, listOf(Ingredient("zucchini"), Ingredient("eggplant"), Ingredient("bell pepper"), Ingredient("tomato")), 4),
        Recipe("Beef Stroganoff", 45, listOf(Ingredient("beef"), Ingredient("sour cream"), Ingredient("onion"), Ingredient("mushroom")), 5),
        Recipe("Pad Thai", 30, listOf(Ingredient("rice noodles"), Ingredient("egg"), Ingredient("peanut"), Ingredient("shrimp")), 4),
        Recipe("Ceviche", 20, listOf(Ingredient("fish"), Ingredient("lime"), Ingredient("onion"), Ingredient("cilantro")), 4),
        Recipe("Churros", 30, listOf(Ingredient("flour"), Ingredient("sugar"), Ingredient("cinnamon"), Ingredient("oil")), 5)
    )
) {

    fun getRecommendationV1(ingredientName: String, minutes: Int): List<Recipe> {
        return recipes
            .filter { it.cookingMinutes <= minutes }
            .filter { it.ingredients.any { ingredient -> ingredient.name == ingredientName } }
            .sortedByDescending { it.stars }
            .take(3)
    }

    fun getRecommendationV2(ingredientName: String, minutes: Int): List<Recipe> {
        return recipes
            .filter { it.isWithinCookingTime(minutes) }
            .filter { it.containsIngredient(ingredientName) }
            .sortedByDescending { it.stars }
            .take(3)
    }
}

data class Recipe(
    val name: String,
    val cookingMinutes: Int,
    var ingredients: List<Ingredient>,
    var stars: Int
) {
    fun isWithinCookingTime(minutes: Int): Boolean {
        return this.cookingMinutes <= minutes
    }

    fun containsIngredient(ingredientName: String): Boolean {
        return this.ingredients.any { it.name == ingredientName }
    }
}

data class Ingredient(val name: String)
