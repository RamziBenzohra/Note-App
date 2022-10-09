package com.rb.note_saving.domain

sealed class AuthenticationEvents(){
    object Register:AuthenticationEvents()
    object Login:AuthenticationEvents()
}
