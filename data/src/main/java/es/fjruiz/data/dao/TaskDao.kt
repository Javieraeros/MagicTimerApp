package es.fjruiz.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import es.fjruiz.data.dto.TaskDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * from TaskDTO")
    fun getTasks(): Flow<List<TaskDTO>>

    @Insert
    fun insertTask(taskDTO: TaskDTO)

    @Update
    fun updateTask(taskDTO: TaskDTO)

    @Query("DELETE FROM TaskDTO where id = :id")
    fun deleteTask(id: Long)
}