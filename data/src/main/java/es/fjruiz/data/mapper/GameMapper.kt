package es.fjruiz.data.mapper

import es.fjruiz.data.dto.GameDTO
import es.fjruiz.data.dto.GameWithPlayersDTO
import es.fjruiz.data.dto.PlayerDTO
import es.fjruiz.domain.model.Game
import es.fjruiz.domain.model.Player

fun GameWithPlayersDTO.toModel(): Game = Game(
    id,
    players.map { it.toModel() },
    isPaused,
    isFinished
)

fun PlayerDTO.toModel(): Player = Player(
    playerId,
    name,
    image,
    timeLeft,
    hasTurn,
    hasPriority,
    extraTimeLeft,
    isExtraTimeRunning
)

fun Game.toDTO(): GameWithPlayersDTO = GameWithPlayersDTO(
    GameDTO(
        id,
        isPaused,
        isFinished
    ),
    players.map { it.toDTO(id) }
)

fun Player.toDTO(gameId: Long): PlayerDTO = PlayerDTO(
    playerId,
    name,
    image,
    timeLeft,
    hasTurn,
    hasPriority,
    extraTimeLeft,
    isExtraTimeRunning,
    gameId
)