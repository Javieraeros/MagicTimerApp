package es.fjruiz.domain.usecase.game

import es.fjruiz.domain.repository.GameRepository

class IsActiveGameUC(private val gameRepository: GameRepository) {
    suspend operator fun invoke(): Boolean = gameRepository.isActiveGame()
}