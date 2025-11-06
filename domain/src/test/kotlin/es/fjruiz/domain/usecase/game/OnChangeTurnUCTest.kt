package es.fjruiz.domain.usecase.game

import es.fjruiz.domain.model.Config
import es.fjruiz.domain.model.PlayerNumber
import es.fjruiz.domain.repository.GameRepository
import es.fjruiz.domain.usecase.util.GameProvider
import io.mockk.coEvery
import io.mockk.spyk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class OnChangeTurnUCTest {
    private val config = Config(time = 300, extraTime = 120, playerNumber = PlayerNumber.TWO)

    private lateinit var gameRepository: GameRepository
    private lateinit var onChangeTurnUCTest: OnChangeTurnUC

    @Before
    fun setup() {
        gameRepository = spyk()
        coEvery { gameRepository.getGame(GameProvider.gameID) } returns GameProvider.changeTurnGame
        coEvery { gameRepository.getGame(GameProvider.extraTurnGameId) } returns GameProvider.changeTurnExtraTurnGame
        onChangeTurnUCTest = OnChangeTurnUC(gameRepository)
    }

    @Test
    fun `Change turn returns same time as in config `() = runTest {
        val result = onChangeTurnUCTest(config, GameProvider.gameID, "1")
        assert(result?.players?.all { it.timeLeft == config.time } == true)
    }


    @Test
    fun `Change turn gives priority to player with turn`() = runTest {
        val playerId = "1"
        val result = onChangeTurnUCTest(config, GameProvider.gameID, playerId)
        assert(result?.players?.first { it.playerId == playerId }?.hasPriority == true)
    }

    @Test
    fun `Change turn takes priority to player without turn`() = runTest {
        val playerId = "1"
        val result = onChangeTurnUCTest(config, GameProvider.gameID, playerId)
        assert(result?.players?.first { it.playerId != playerId }?.hasPriority == false)
    }

    @Test
    fun `Change turn makes all player not running extra time`() = runTest {
        val playerId = "1"
        val result = onChangeTurnUCTest(config, GameProvider.gameID, playerId)
        assert(result?.players?.none { it.isExtraTimeRunning } == true)
    }

    @Test
    fun `Change turn makes current player with turn has one extra turn if no extra time used`() = runTest {
        val playerId = "3"
        val result = onChangeTurnUCTest(config, GameProvider.extraTurnGameId, playerId)
        assert(result?.players?.first { !it.hasTurn }?.extraTimeLeft == 1)
    }
}