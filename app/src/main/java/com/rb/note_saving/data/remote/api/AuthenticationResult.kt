package com.rb.note_saving.data.remote.api

sealed class AuthenticationResult <T>( val data:T? = null,val message:String?=null){


    object UserAuthenticated :AuthenticationResult<Unit>()
    class UserNotAuthenticated <T>(message: String):AuthenticationResult<T>(message = message)
    class AuthenticationError<T> :AuthenticationResult<T>()
}
