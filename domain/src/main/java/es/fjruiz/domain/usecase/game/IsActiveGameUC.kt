package es.fjruiz.domain.usecase.game

import es.fjruiz.domain.repository.GameRepository
import javax.inject.Inject

class IsActiveGameUC @Inject constructor(private val gameRepository: GameRepository) {
    suspend operator fun invoke(): Boolean = gameRepository.isActiveGame()
}