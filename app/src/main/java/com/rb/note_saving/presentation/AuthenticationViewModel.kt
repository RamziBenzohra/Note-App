package com.rb.note_saving.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rb.note_saving.data.entities.UserCredential
import com.rb.note_saving.data.remote.api.AuthenticationResult
import com.rb.note_saving.domain.AuthenticationEvents
import com.rb.note_saving.domain.AuthenticationScreenEvents
import com.rb.note_saving.domain.AuthenticationState
import com.rb.note_saving.domain.AuthenticationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel
@Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases
    ) :ViewModel() {


    var authenticationState by mutableStateOf(AuthenticationState())

    private val _authResult = Channel<AuthenticationResult<Unit>> {  }
    val authResult = _authResult.receiveAsFlow()


    fun onAuthScreenEvent(events: AuthenticationScreenEvents){
        when(events){
            is AuthenticationScreenEvents.OnLoginEvent->{
                loginUser(UserCredential(authenticationState.loginUsername,authenticationState.loginPassword))
            }
            is AuthenticationScreenEvents.OnRegisterEvent->{
                registerUser(UserCredential(authenticationState.registerUsername,authenticationState.registerPassword))

            }
            is AuthenticationScreenEvents.RegisterUsernameChangedEvent->{
                authenticationState=authenticationState.copy(
                    registerUsername = events.data!!
                )
            }
            is AuthenticationScreenEvents.RegisterPasswordChangedEvent->{
                authenticationState=authenticationState.copy(
                    registerPassword = events.data!!
                )
            }
            is AuthenticationScreenEvents.LoginUsernameChangedEvent->{
                authenticationState=authenticationState.copy(
                    loginUsername = events.data!!
                )
            }
            is AuthenticationScreenEvents.LoginPasswordChangedEvent->{
                authenticationState=authenticationState.copy(
                    loginPassword = events.data!!
                )
            }

        }
    }



    private  fun registerUser(credential: UserCredential){
        viewModelScope.launch {
            authenticationState = authenticationState.copy(isLoading = true)
            val result =authenticationUseCases(AuthenticationEvents.Register, userCredential = credential)
            authenticationState = authenticationState.copy(isLoading = false)
            _authResult.send(result)
        }
    }





    private  fun loginUser(credential: UserCredential){
        viewModelScope.launch {
            authenticationState = authenticationState.copy(isLoading = true)
            val result =authenticationUseCases(AuthenticationEvents.Login, userCredential = credential)
            authenticationState = authenticationState.copy(isLoading = false)
            _authResult.send(result)
        }
    }
}