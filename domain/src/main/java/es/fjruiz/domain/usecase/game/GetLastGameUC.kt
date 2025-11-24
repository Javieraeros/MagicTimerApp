package es.fjruiz.domain.usecase.game

import es.fjruiz.domain.model.Game
import es.fjruiz.domain.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLastGameUC @Inject constructor(private val gameRepository: GameRepository) {
    suspend operator fun invoke(): Flow<Game> = gameRepository.getLastGame()
}