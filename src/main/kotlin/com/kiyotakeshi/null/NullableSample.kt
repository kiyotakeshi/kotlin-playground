package com.kiyotakeshi.`null`

data class Movie(
    val id: Int?,
    val name: String
)

fun printName(name: String) {
    println("name is : $name")
}

fun printName2(name: String?) {
    println("name is : $name")
}

fun main() {

    var nullableSample: String? = null
    println("value is : ${nullableSample?.length}") // ?. safe operator
    nullableSample = "sample"
    println("length is : ${nullableSample.length}") // we don't need to add safe operator for non-null variable

    println("------------------")
    var nullableSample2: String? = null
    val length = nullableSample2?.length?.toLong() ?: 0 // ?: elvis operator returns default value if null
    println("length is : $length")

    println("------------------")
    var nullableSample3: String? = null

    // .run scope function
    nullableSample3?.run {
        printName(this)
    }

    printName2(nullableSample3)

    println("------------------")
    // Variable 'noneNullSample' must be initialized
    var noneNullSample: String = "none null"
    println("value is : $noneNullSample")

    println("------------------")
    val movie = Movie(null, "8mile")
    val savedMovie = saveMovie(movie)
    println(savedMovie.id!!) // !! non-null assertions makes sure the value is not null(if null, Null Pointer Exception occurs)
    println("saved movie : $savedMovie")
}

fun saveMovie(movie: Movie): Movie {
    // return movie
    return movie.copy(id = 1)
}
