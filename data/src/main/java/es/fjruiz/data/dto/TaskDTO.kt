package es.fjruiz.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskDTO(
    @PrimaryKey val id: Long,
    val value: String,
    val done: Boolean
)
