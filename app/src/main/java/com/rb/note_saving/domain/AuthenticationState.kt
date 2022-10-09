package com.rb.note_saving.domain

data class AuthenticationState (
    val isLoading: Boolean = false,
    val registerUsername: String = "",
    val registerPassword: String = "",
    val loginUsername: String = "",
    val loginPassword: String = ""
        )