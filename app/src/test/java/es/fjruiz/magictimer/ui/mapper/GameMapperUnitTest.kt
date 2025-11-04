package es.fjruiz.magictimer.ui.mapper

import org.junit.Test
import kotlin.test.assertEquals

class GameMapperUnitTest {

    @Test
    fun `toTime expect zeros before number`() {
        assertEquals(100L.toTime(), "01:40")
    }

    @Test
    fun `toTime expect two zeros if no minutes`() {
        assertEquals(30L.toTime(), "00:30")
    }

    @Test
    fun `toTime expect not to show hours`() {
        assertEquals(3600L.toTime(), "00:00")
    }
}