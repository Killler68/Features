package com.example.features.notes.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesTaskDao {

    @Query("SELECT * FROM grid_table WHERE userId = :userId AND type = 'TASK'")
    suspend fun getTasks(userId: Int): List<NoteTask>

    @Query("SELECT * FROM grid_table WHERE userId = :userId AND type = 'NOTE'")
    suspend fun getNotes(userId: Int): List<NoteTask>

    @Query("SELECT * FROM grid_table WHERE userId = :userId AND id = :noteId")
    suspend fun getNoteByNoteId(userId: Int, noteId: Int): NoteTask?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createNotesTask(noteTask: NoteTask)

    @Query("DELETE FROM grid_table WHERE id = :id")
    suspend fun deleteNotesTask(id: Int)

    @Update
    suspend fun updateNotesTask(noteTask: NoteTask)
}