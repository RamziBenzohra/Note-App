package com.rb.note_saving.data.authentication

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import com.rb.note_saving.data.remote.api.AuthenticationResult
import com.rb.note_saving.data.remote.api.Api
import com.rb.note_saving.data.remote.api.AuthenticationRequest
import com.rb.note_saving.utils.Constants.SHARED_PREFERENCES_TOKEN_KEY
import retrofit2.HttpException

class AuthenticationRepositoryImpl (
    private val api:Api,
    private val sharedPreferences: SharedPreferences

): AuthenticationRepository {
    override suspend fun register(username: String, password: String): AuthenticationResult<Unit> {
        return try {
            val request=api.registerNewUser(
               AuthenticationRequest(username, password)
            )
            if (request.isSuccessful){
                login(username, password)
            }else{
                AuthenticationResult.AuthenticationError()
            }
        }catch (e: HttpException){
            if (e.code() == 401){
                AuthenticationResult.UserNotAuthenticated("Unauthorized")
            }else{
                AuthenticationResult.AuthenticationError()
            }
        }catch (e:Exception){
            AuthenticationResult.AuthenticationError()
        }
    }

    override suspend fun login(username: String, password: String): AuthenticationResult<Unit> {
        return try {
            val userToken=api.loginNewUser(AuthenticationRequest(username, password))
            sharedPreferences.edit().putString(SHARED_PREFERENCES_TOKEN_KEY,userToken.toString()).apply()
            AuthenticationResult.UserAuthenticated
        }catch (e: HttpException){
            if (e.code() == 401){
                AuthenticationResult.UserNotAuthenticated("Unauthorized Operation")
            }else{
                AuthenticationResult.AuthenticationError()
            }
        }catch (e:Exception){
            e.printStackTrace()
            AuthenticationResult.AuthenticationError()
        }
    }
}