package com.example.brainiac

import org.junit.Assert.*
import org.junit.Test

class DisplaySizeTest {
    private val classUnderTest = DisplaySize()

    @Test
    fun validateCalculateDisplaySize() {
        val result = classUnderTest.calculateDisplaySize(
            countYes = 3
        )
        assertEquals(0.21f, result, 0.001f)
    }
}