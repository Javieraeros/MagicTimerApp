package es.fjruiz.magictimer.ui.component.card

import org.junit.Test
import kotlin.test.assertEquals

class CounterCardModelUnitTest {

    private fun model(
        timeLeft: Long,
        totalTime: Long
    ) = CounterCardModel(
        time = "00:00",
        timeLeft = timeLeft,
        totalTime = totalTime,
        image = "",
        hasTurn = false,
        hasPriority = false,
        stars = StarEnum.NONE,
        totalTimeConsumed = "00:00"
    )

    @Test
    fun `full time left expects full fraction`() {
        assertEquals(1f, model(timeLeft = 300, totalTime = 300).timeLeftFraction)
    }

    @Test
    fun `half time left expects half fraction`() {
        assertEquals(0.5f, model(timeLeft = 150, totalTime = 300).timeLeftFraction)
    }

    @Test
    fun `no time left expects zero fraction`() {
        assertEquals(0f, model(timeLeft = 0, totalTime = 300).timeLeftFraction)
    }

    @Test
    fun `more time than total expects fraction clamped to one`() {
        assertEquals(1f, model(timeLeft = 400, totalTime = 300).timeLeftFraction)
    }

    @Test
    fun `zero total expects zero fraction`() {
        assertEquals(0f, model(timeLeft = 100, totalTime = 0).timeLeftFraction)
    }

    @Test
    fun `negative total expects zero fraction`() {
        assertEquals(0f, model(timeLeft = 100, totalTime = -1).timeLeftFraction)
    }
}
