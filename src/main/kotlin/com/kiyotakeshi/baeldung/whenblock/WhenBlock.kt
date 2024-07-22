package com.kiyotakeshi.baeldung.whenblock

// https://www.baeldung.com/kotlin/when
enum class UnixFileType {
    D, HYPHEN_MINUS, L
}

sealed class UnixFile {
    abstract fun getFileType(): UnixFileType

    class RegularFile(val content: String) : UnixFile() {
        override fun getFileType(): UnixFileType {
            return UnixFileType.HYPHEN_MINUS
        }
    }

    class Directory(val children: List<UnixFile>) : UnixFile() {
        override fun getFileType(): UnixFileType {
            return UnixFileType.D
        }
    }

    class SymbolicLink(val originalFile: UnixFile) : UnixFile() {
        override fun getFileType(): UnixFileType {
            return UnixFileType.L
        }
    }
}

fun isPositiveInt(number: Int): Boolean {
    return when (number) {
        0 -> {
            println("number is zero.")
            print("It's neither positive nor negative.")
            return false
        }

        in -1 downTo Int.MIN_VALUE -> {
            print("number is negative")
            return false
        }

        else -> {
            print("number is positive")
            return true
        }
    }
}
