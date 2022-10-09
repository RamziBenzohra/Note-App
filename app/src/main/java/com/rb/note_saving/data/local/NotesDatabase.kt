package com.rb.note_saving.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rb.note_saving.data.entities.Note

@Database(entities = [Note::class], version = 1)
abstract class NotesDatabase () :RoomDatabase(){
    abstract fun notesDao(): NotesDAO
}