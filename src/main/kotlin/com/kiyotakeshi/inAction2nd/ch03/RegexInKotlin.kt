package com.kiyotakeshi.inAction2nd.ch03

fun main() {
    println("12.345-6.A".split("\\.|-".toRegex()))
    // regex に頼らなくても文字列を操作できる
    println("12.345-6.A".split(".", "-"))
}

fun parsePath(path: String): String {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")

    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")

    return "dir: $directory, name: $fileName, ext: $extension"
}

fun parsePathRegex(path: String): String {
    val regex = """
        (.+)/(.+)\.(.+)
    """.trimIndent().toRegex()
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory, filename, extension) = matchResult.destructured
        return "dir: $directory, name: $filename, ext: $extension"
    }
    return "not match"
}
