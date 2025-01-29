package com.example.features.common.database.task

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {

    @Query("SELECT * FROM task_table WHERE userId = :userId")
    suspend fun getTasks(userId: Int): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTask(task: Task)

    @Query("DELETE FROM task_table WHERE taskId = :taskId")
    suspend fun deleteTask(taskId: Int)

    @Update
    suspend fun updateTask(task: Task)
}