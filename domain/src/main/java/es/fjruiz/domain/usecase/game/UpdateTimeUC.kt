package es.fjruiz.domain.usecase.game

import es.fjruiz.domain.model.Config
import es.fjruiz.domain.model.Game
import es.fjruiz.domain.model.Player
import es.fjruiz.domain.repository.GameRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlin.coroutines.coroutineContext

class UpdateTimeUC(private val gameRepository: GameRepository) {

    suspend operator fun invoke(config: Config, game: Game) {
        val updatedGame = updateTimer(config, game)
        if (coroutineContext.isActive) {
            gameRepository.updateGame(updatedGame)
        }
    }

    private fun updateTimer(config: Config, game: Game): Game {
        val updatedPlayers = game.players.map { player ->
            if (player.hasPriority) {
                updatePriorityPlayer(config, player)
            } else {
                player
            }
        }

        return game.copy(players = updatedPlayers)
    }

    private fun updatePriorityPlayer(config: Config, player: Player): Player =
        when {
            player.timeLeft > 0 -> {
                player.copy(timeLeft = player.timeLeft - 1)
            }

            player.timeLeft == 0L && player.extraTimeLeft > 0 -> {
                player.copy(
                    timeLeft = config.extraTime,
                    extraTimeLeft = player.extraTimeLeft - 1,
                    isExtraTimeRunning = true
                )
            }

            else -> {
                player.copy(timeLeft = 0, extraTimeLeft = 0)
            }
        }
}