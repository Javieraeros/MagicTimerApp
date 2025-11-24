package es.fjruiz.domain.usecase.game

import es.fjruiz.domain.model.Config
import es.fjruiz.domain.model.Game
import es.fjruiz.domain.model.Player
import es.fjruiz.domain.repository.GameRepository
import javax.inject.Inject

const val jace =
    "https://images.ctfassets.net/s5n2t79q9icq/5Z5BZ90db9laZwDh9hO7RP/83ea76b645bfaad7973099d009f67356/jace-beleren-1920.jpg?q=80"
const val chandra =
    "https://images.ctfassets.net/s5n2t79q9icq/4Ccsre2U4012DgUfGcKYJp/ab0c9dabdb0c2869e4755f8d6c872ed2/chandra-nalaar-1920.jpg?q=80"
const val vivien =
    "https://images.ctfassets.net/s5n2t79q9icq/XbA9mWj3ix8WYrv464zoJ/4cd267cb17f450b5aa4dcac205187f93/vivien-reid-1920.jpg?q=80"
const val ajani =
    "https://images.ctfassets.net/s5n2t79q9icq/2t3q9lomkTzsNhudK7mbCW/5f73e67aadfdaa94fcd08988dcfca558/ajani-1920.jpg?q=80"
private val imageList = listOf(
    jace,
    chandra,
    vivien,
    ajani
)

class CreateGameUC @Inject constructor(private val gameRepository: GameRepository) {
    suspend operator fun invoke(config: Config): Game {
        val lastGame = gameRepository.getLastUnfinishedGame()
        val game = createGame(config)
        gameRepository.createGame(game)

        lastGame?.copy(isFinished = true)?.let { gameRepository.updateGame(it) }
        return game
    }

    internal fun createGame(config: Config): Game {
        val range = 0 until config.playerNumber.value
        val players: List<Player> = range.map {
            Player(
                name = "",
                image = imageList[it],
                timeLeft = config.time,
                hasTurn = false,
                hasPriority = false,
                extraTimeLeft = 2,
                isExtraTimeRunning = false
            )
        }
        return Game(System.currentTimeMillis(), players, false, false)
    }
}