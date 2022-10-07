package com.rb.note_saving.domain

import com.rb.note_saving.data.Note
import com.rb.note_saving.data.remote.api.NotesApi
import kotlinx.coroutines.flow.Flow

class RepositoryImpl (api: NotesApi) : Repository {
    override suspend fun getNotes(): Flow<List<Note>> {
        TODO("Not yet implemented")
    }
}