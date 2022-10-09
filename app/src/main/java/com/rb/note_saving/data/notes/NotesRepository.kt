package com.rb.note_saving.data.notes

import com.rb.note_saving.data.entities.Note
import com.rb.note_saving.data.remote.api.ResponseFromServer
import kotlinx.coroutines.flow.Flow

interface NotesRepository{
    suspend fun getNotesFromDatabase():Flow<List<Note>>
    suspend fun getNoteByNoteId(id:String):Flow<Note>
    suspend fun addNotesOnDatabase(notes: List<Note>):Boolean
    suspend fun getNotesFromApi(token:String,userId:String):List<Note>
}