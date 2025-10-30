package es.fjruiz.domain.usecase.game

import es.fjruiz.domain.model.Game
import es.fjruiz.domain.repository.GameRepository

class UpdateGameUC(private val gameRepository: GameRepository) {
    suspend operator fun invoke(game: Game) = gameRepository.updateGame(game)
}