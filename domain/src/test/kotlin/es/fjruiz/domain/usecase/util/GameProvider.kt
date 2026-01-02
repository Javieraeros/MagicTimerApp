package es.fjruiz.domain.usecase.util

import es.fjruiz.domain.model.Game
import es.fjruiz.domain.model.Player
import kotlin.random.Random

object GameProvider {
    val gameID = 0L
    val extraTurnGameId = 1L
    val changeTurnGame = Game(
        gameID,
        listOf(
            Player(
                playerId = "1",
                name = "",
                image = "",
                timeLeft = Random.nextLong(),
                Random.nextBoolean(),
                Random.nextBoolean(),
                2,
                Random.nextBoolean(),
                totalTimeConsumed = 0L
            ),
            Player(
                playerId = "2",
                name = "",
                image = "",
                timeLeft = Random.nextLong(),
                Random.nextBoolean(),
                Random.nextBoolean(),
                2,
                Random.nextBoolean(),
                totalTimeConsumed = 0L
            )
        ),
        false,
        false
    )

    val changeTurnExtraTurnGame = Game(
        extraTurnGameId,
        listOf(
            Player(
                playerId = "3",
                name = "",
                image = "",
                timeLeft = Random.nextLong(),
                hasTurn = Random.nextBoolean(),
                hasPriority = Random.nextBoolean(),
                extraTimeLeft = 2,
                isExtraTimeRunning = Random.nextBoolean(),
                totalTimeConsumed = 0L
            ),
            Player(
                playerId = "4",
                name = "",
                image = "",
                timeLeft = Random.nextLong(),
                hasTurn = true,
                hasPriority = Random.nextBoolean(),
                extraTimeLeft = 0,
                isExtraTimeRunning = false,
                totalTimeConsumed = 0L
            )
        ),
        false,
        false
    )
}