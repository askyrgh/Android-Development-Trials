package com.askyr.tip_time

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorUnitTests {
    @Test
    fun calculateTip_20PercentNoRoundup() {
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(amount = amount, tipPercent = tipPercent, false)
        assertEquals(expectedTip, actualTip)
    }
    @Test
    fun calculateTip_15PercentRoundup() {
        val amount = 10.00
        val tipPercent = 15.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(1)
        val actualTip = calculateTip(amount = amount, tipPercent = tipPercent, true)
        assertEquals(expectedTip, actualTip)
    }
}