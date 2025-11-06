package es.fjruiz.domain.usecase.game

import es.fjruiz.domain.model.Game
import es.fjruiz.domain.repository.GameRepository

class GetGameUC(private val gameRepository: GameRepository) {
    suspend operator fun invoke(id: Long): Game? = gameRepository.getGame(id)
}