package com.rb.note_saving.presentation

import androidx.lifecycle.ViewModel
import com.rb.note_saving.data.notes.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel   @Inject constructor(val repository: NotesRepository) :ViewModel() {
}