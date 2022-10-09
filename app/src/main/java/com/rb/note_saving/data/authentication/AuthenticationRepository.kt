package com.rb.note_saving.data.authentication

import com.rb.note_saving.data.remote.api.AuthenticationResult


interface AuthenticationRepository {
    suspend fun register(username:String,password:String): AuthenticationResult<Unit>
    suspend fun login(username:String,password:String):AuthenticationResult<Unit>

}