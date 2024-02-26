package com.example.brainiac

import org.junit.Assert.*
import org.junit.Test

//testing correct functioning of DisplaySize() calculation
class DisplaySizeTest {
    private val classUnderTest = DisplaySize()

    @Test
    fun validateCalculateDisplaySize() {
        val result1 = classUnderTest.calculateDisplaySize(
            countYes = 7
        )
        val result2 = classUnderTest.calculateDisplaySize(
            countYes = 0
        )
        val result3 = classUnderTest.calculateDisplaySize(
            countYes = 14
        )
        assertEquals(0.49f, result1, 0.001f)
        assertEquals(0.0f, result2, 0.001f)
        assertEquals(0.98f, result3, 0.001f)
    }
}