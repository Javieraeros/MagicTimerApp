package es.fjruiz.domain.usecase.game

import es.fjruiz.domain.model.Game
import es.fjruiz.domain.repository.GameRepository
import javax.inject.Inject

class GetGameUC @Inject constructor(private val gameRepository: GameRepository) {
    suspend operator fun invoke(id: Long): Game? = gameRepository.getGame(id)
}