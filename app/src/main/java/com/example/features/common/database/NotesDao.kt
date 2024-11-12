package com.example.features.common.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes_table WHERE userId = :userId")
    suspend fun getNotes(userId: Int): List<Notes>

    @Query("SELECT * FROM notes_table WHERE noteId = :notesId")
    suspend fun getNoteById(notesId: Int): Notes?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(notes: Notes)

    @Query("DELETE FROM notes_table WHERE noteId = :noteId")
    suspend fun deleteNote(noteId: Int)

    @Update
    suspend fun updateNote(notes: Notes)
}