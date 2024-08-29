package com.kiyotakeshi.baeldung.whenblock

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class WhenBlockKtTests {

    private val outputStream = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStream))
    }

    @AfterEach
    fun tearDown() {
        outputStream.reset()
    }

    @Test
    fun testPositiveIntWithZero() {
        val givenNumber = 0
        val expectedOutput = "number is zero.\nIt's neither positive nor negative."
        val isPositive = isPositiveInt(givenNumber)
        assertFalse(isPositive)
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testPositiveIntWithNegativeValue() {
        val givenNumber = -5
        val expectedOutput = "number is negative"
        val isPositive = isPositiveInt(givenNumber)
        assertFalse(isPositive)
        assertEquals(expectedOutput, outputStream.toString())
    }

    @Test
    fun testPositiveIntWithPositiveValue() {
        val givenNumber = 10
        val expectedOutput = "number is positive"
        val isPositive = isPositiveInt(givenNumber)
        assertTrue(isPositive)
        assertEquals(expectedOutput, outputStream.toString())
    }
}
