package com.rb.note_saving.domain


sealed class AuthenticationScreenEvents{
    object OnRegisterEvent:AuthenticationScreenEvents()
    object OnLoginEvent:AuthenticationScreenEvents()
    data class RegisterUsernameChangedEvent(val data: String?=null):AuthenticationScreenEvents()
    data class RegisterPasswordChangedEvent(val data: String?=null):AuthenticationScreenEvents()
    data class LoginUsernameChangedEvent(val data: String?=null):AuthenticationScreenEvents()
    data class LoginPasswordChangedEvent(val data: String?=null):AuthenticationScreenEvents()
}
