package com.rb.note_saving.domain

import android.content.SharedPreferences
import com.rb.note_saving.data.authentication.AuthenticationRepository
import com.rb.note_saving.data.entities.UserCredential
import com.rb.note_saving.data.remote.api.AuthenticationResult

data class AuthenticationUseCases(
    private val authenticationRepository: AuthenticationRepository,
){
    suspend operator fun invoke(authenticationEvents: AuthenticationEvents,userCredential: UserCredential): AuthenticationResult<Unit> {
       return when(authenticationEvents){
            is AuthenticationEvents.Register->{
                authenticationRepository.register(
                    username = userCredential.userName,
                    password = userCredential.password
                )
            }
            is AuthenticationEvents.Login->{
                authenticationRepository.login(
                    username = userCredential.userName,
                    password = userCredential.password
                )
            }
        }
    }
}
