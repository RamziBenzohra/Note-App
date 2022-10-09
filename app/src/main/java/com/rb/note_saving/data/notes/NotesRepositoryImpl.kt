package com.rb.note_saving.data.notes

import com.rb.note_saving.data.entities.Note
import com.rb.note_saving.data.local.NotesDAO
import com.rb.note_saving.data.remote.api.Api
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class NotesRepositoryImpl (private val notesDAO: NotesDAO,private val api:Api) : NotesRepository {
    override suspend fun getNotesFromDatabase(): Flow<List<Note>> {
        return try {
                notesDAO.getAllNotes()
        }catch (e:Exception){
            e.printStackTrace()
            flow { }
        }
    }

    override suspend fun getNoteByNoteId(id:String): Flow<Note> {
        return try {
            notesDAO.getNoteByNoteId(id)
        }catch (e:Exception){
            e.printStackTrace()
            flow {  }
        }
    }

    override suspend fun addNotesOnDatabase(notes: List<Note>):Boolean {
        return try {
            notesDAO.insertAll(notes)
            true
        }catch (e:Exception){
            e.printStackTrace()
           false
        }
    }

    override suspend fun getNotesFromApi(token:String,userId:String): List<Note> {
        return try {
            api.getAllNote(token, userId)
        }catch (e:HttpException){
            e.printStackTrace()
            emptyList()
        }catch (e:Exception){
            e.printStackTrace()
            emptyList()
        }
    }
}