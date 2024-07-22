package com.kiyotakeshi.baeldung.whenblock

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UnixFileTests {
    @Test
    fun whenExpression() {
        val directoryType: UnixFileType = UnixFileType.D

        // A big difference from Java’s switch statement is that
        // we can use the when{} block in Kotlin **both as a statement and as an expression.**
        // Flow-control structures are expressions and the result of their evaluation can be returned to the caller.
        val objectType = when (directoryType) {
            UnixFileType.D -> "d"
            UnixFileType.HYPHEN_MINUS -> "-"
            UnixFileType.L -> "l"
        }

        assertEquals("d", objectType)
    }

    @Test
    fun whenExpressionWithDefaultCase() {
        val fileType = UnixFileType.L

        val result = when (fileType) {
            UnixFileType.D -> "d"
            else -> "invalid"
        }

        assertEquals("invalid", result)
    }

    @Test
    fun whenExpressionWithThrowException() {
        val fileType = UnixFileType.L

        val exception = org.junit.jupiter.api.assertThrows<IllegalArgumentException> {
            when (fileType) {
                UnixFileType.D -> "d"
                UnixFileType.HYPHEN_MINUS -> "-"
                else -> throw IllegalArgumentException("Invalid file type")
            }
        }

        assertEquals("Invalid file type", exception.message)
    }

    @Test
    fun whenStatement() {
        val fileType = UnixFileType.HYPHEN_MINUS

        when (fileType) {
            UnixFileType.D -> println("Directory")
            UnixFileType.HYPHEN_MINUS -> println("Regular file")
            UnixFileType.L -> println("Symbolic link")
        }
    }

    @Test
    fun whenCaseCombination() {
        val fileType = UnixFileType.D

        val frequentFileType: Boolean = when (fileType) {
            UnixFileType.D, UnixFileType.HYPHEN_MINUS -> true
            else -> false
        }

        assertTrue(frequentFileType)
    }

    @Test
    fun whenWithoutArgument() {
        val fileType = UnixFileType.L

        val objectType = when {
            fileType == UnixFileType.D -> "d"
            fileType == UnixFileType.HYPHEN_MINUS -> "-"
            fileType == UnixFileType.L -> "l"
            else -> "invalid"
        }

        assertEquals("l", objectType)
    }

    @Test
    fun whenCollectionCaseExpressions() {
        val regularFile = UnixFile.RegularFile("content")
        val symbolicLink = UnixFile.SymbolicLink(regularFile)
        val directory = UnixFile.Directory(listOf(regularFile, symbolicLink))

        val isRegularFileInDirectory = when (regularFile) {
            // Kotlin provides the in operator, which is syntactic sugar for the contains() method.
            in directory.children -> true
            else -> false
        }

        val isSymbolicLinkInDirectory = when {
            symbolicLink in directory.children -> true
            else -> false
        }

        assertTrue(isRegularFileInDirectory)
        assertTrue(isSymbolicLinkInDirectory)
    }

    @Test
    fun whenCollectionRangeCaseExpressions() {
        val fileType = UnixFileType.HYPHEN_MINUS

        val isCorrectType = when (fileType) {
            in UnixFileType.D..UnixFileType.L -> true
            else -> false
        }

        assertTrue(isCorrectType)

    }

    @Test
    fun whenWithIsOperatorWithSmartCase() {
        // file
        val unixFile: UnixFile = UnixFile.RegularFile("test content")
        val result = getUnixFileDetails(unixFile)

        assertEquals("test content", result)

        // directory
        val unixDirectory = UnixFile.Directory(listOf(unixFile, UnixFile.SymbolicLink(unixFile)))
        val directoryResult = getUnixFileDetails(unixDirectory)

        assertEquals("HYPHEN_MINUS, L", directoryResult)

        // symbolic link
        val unixSymbolicLink = UnixFile.SymbolicLink(unixFile)
        val symbolicLinkResult = getUnixFileDetails(unixSymbolicLink)

        assertEquals("HYPHEN_MINUS", symbolicLinkResult)
    }

    private fun getUnixFileDetails(unixFile: UnixFile): String {
        return when (unixFile) {
            is UnixFile.RegularFile -> unixFile.content
            is UnixFile.Directory -> unixFile.children.map { it.getFileType() }.joinToString(", ")
            is UnixFile.SymbolicLink -> unixFile.originalFile.getFileType().name
        }
    }
}