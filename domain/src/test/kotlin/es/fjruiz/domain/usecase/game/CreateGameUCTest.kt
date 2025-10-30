package es.fjruiz.domain.usecase.game

import es.fjruiz.domain.model.Config
import es.fjruiz.domain.model.PlayerNumber
import es.fjruiz.domain.repository.GameRepository
import io.mockk.Called
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CreateGameUCTest {
    private lateinit var gameRepository: GameRepository
    private lateinit var createGameUC: CreateGameUC

    @Before
    fun setup() {
        gameRepository = spyk()
        createGameUC = CreateGameUC(gameRepository)
    }

    @Test
    fun `Create game expect same number of players as in config`() {
        val config = Config(playerNumber = PlayerNumber.THREE)
        val game = createGameUC.createGame(config)

        assert(game.players.size == config.playerNumber.value)
    }

    @Test
    fun `Create game expect greater Id than before game`() = runTest {
        val config = Config(playerNumber = PlayerNumber.THREE)
        val firstGame = createGameUC.createGame(config)
        delay(1000L)
        val secondGame = createGameUC.createGame(config)

        assert(firstGame.id < secondGame.id)
    }

    @Test
    fun `Create game expect same remaining time in each players as in config`() {
        val config = Config(playerNumber = PlayerNumber.THREE)
        val firstGame = createGameUC.createGame(config)

        assert(firstGame.players.all { it.timeLeft == config.time })
    }

    @Test
    fun `Create game expect 2 extra time remaining`() {
        val config = Config(playerNumber = PlayerNumber.THREE)
        val firstGame = createGameUC.createGame(config)

        assert(firstGame.players.all { it.extraTimeLeft == 2 })
    }

    @Test
    fun `Create game expect call to repository`() = runTest {
        val config = Config(playerNumber = PlayerNumber.THREE)
        val game = createGameUC.invoke(config)

        coVerify(exactly = 1) { gameRepository.createGame(game) }
    }
}