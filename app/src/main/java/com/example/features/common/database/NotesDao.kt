package com.example.features.common.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NotesDao {

    @Query("SELECT * FROM notes_table")
    suspend fun getNotes(): List<Notes>

    @Query("SELECT * FROM notes_table WHERE id = :notesId")
    suspend fun getNoteById(notesId: Int): Notes?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(notes: Notes)

    @Delete
    suspend fun deleteNote(notes: Notes)

    @Update
    suspend fun updateNote(notes: Notes)
}