package com.rb.note_saving.presentation

import androidx.lifecycle.ViewModel
import com.rb.note_saving.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModel   @Inject constructor(val repository: Repository) :ViewModel() {
}