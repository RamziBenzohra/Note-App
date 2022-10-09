package com.rb.note_saving.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.rb.note_saving.data.entities.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDAO {
    @Query("SELECT * FROM Note")
     fun getAllNotes():Flow<List<Note>>

    @Query("SELECT * FROM Note WHERE id = :id")
     fun  getNoteByNoteId(id:String):Flow<Note>

     @Insert
     suspend fun insertAll(list: List<Note>)

}