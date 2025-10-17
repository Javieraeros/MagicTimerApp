package es.fjruiz.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import es.fjruiz.data.dao.TaskDao
import es.fjruiz.data.dto.TaskDTO

@Database(
    [
        TaskDTO::class
    ]
    , version = 1
)
abstract class BaseDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

}