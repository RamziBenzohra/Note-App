package com.rb.note_saving.domain

import com.rb.note_saving.data.Note
import com.rb.note_saving.data.remote.api.NotesApi
import kotlinx.coroutines.flow.Flow

interface Repository{
    suspend fun getNotes():Flow<List<Note>>
}