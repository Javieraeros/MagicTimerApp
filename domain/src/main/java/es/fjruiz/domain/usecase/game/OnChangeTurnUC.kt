package es.fjruiz.domain.usecase.game

import es.fjruiz.domain.model.Config
import es.fjruiz.domain.model.Game
import es.fjruiz.domain.repository.GameRepository
import javax.inject.Inject

class OnChangeTurnUC @Inject constructor(private val gameRepository: GameRepository) {

    suspend operator fun invoke(config: Config, gameId: Long, playerId: String): Game? =
        gameRepository.getGame(gameId)?.let { game ->
                game.copy(players = game.players.map {
                    val extraTimeLeft =
                        if (it.hasTurn && it.isExtraTimeRunning.not() && it.extraTimeLeft < 2) {
                            it.extraTimeLeft + 1
                        } else {
                            it.extraTimeLeft
                        }

                    it.copy(
                        timeLeft = config.time,
                        hasTurn = it.playerId == playerId,
                        hasPriority = it.playerId == playerId,
                        extraTimeLeft = extraTimeLeft,
                        isExtraTimeRunning = false
                    )
                })
    }
}