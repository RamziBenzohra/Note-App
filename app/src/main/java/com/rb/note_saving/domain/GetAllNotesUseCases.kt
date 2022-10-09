package com.rb.note_saving.domain

import com.rb.note_saving.data.entities.Note
import com.rb.note_saving.data.entities.AddNotesException
import com.rb.note_saving.data.notes.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.jvm.Throws

data class GetAllNotesUseCases (private val repository:NotesRepository) {

    @Throws(AddNotesException::class)
    suspend operator fun invoke(token:String,userId:String):Flow<List<Note>>{
        val notes = repository.getNotesFromApi(token, userId)
        return if (notes.isNotEmpty()){
            val isNotesAdded=repository.addNotesOnDatabase(notes)
            if (isNotesAdded){
                repository.getNotesFromDatabase()
            }else{
                throw AddNotesException("Error:failed to add notes to database")
            }
        }else{
            throw AddNotesException("Error : failed get notes from server")
        }
    }
}