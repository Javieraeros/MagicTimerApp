package es.fjruiz.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import es.fjruiz.data.dao.GameDao
import es.fjruiz.data.dao.TaskDao
import es.fjruiz.data.dto.GameDTO
import es.fjruiz.data.dto.PlayerDTO
import es.fjruiz.data.dto.TaskDTO

@Database(
    [
        TaskDTO::class,
        GameDTO::class,
        PlayerDTO::class
    ], version = 1
)
abstract class BaseDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    abstract fun playerDao(): GameDao
}