package com.rb.note_saving.data.remote.api

import androidx.room.Delete
import com.rb.note_saving.data.entities.Note
import retrofit2.Response
import retrofit2.http.*

interface Api {


    @POST("register")
    suspend fun registerNewUser(
        @Body request: AuthenticationRequest
    ):Response<String>

    @POST("login")
    suspend fun loginNewUser(
        @Body request: AuthenticationRequest
    ):ResponseFromServer

    @GET("notes")
    suspend fun getAllNote(
        @Header("Authorization") token:String,
        @Query("userId") userId:String
    ):List<Note>

    @GET("notes/note")
    suspend fun getNote(
        @Header("Authorization") token:String,
        @Query("userId") userId:String,
        @Query("noteId") noteId:String,
    ):Note



    @POST("notes/note")
    suspend fun insertNote(
        @Header("Authorization") token:String,
        @Body note: Note
    ):ResponseFromServer



    @Delete(entity = Note::class)
    suspend fun deleteNote(
        @Header("Authorization") token:String,
        @Query("userId") userId:String,
        @Query("noteId") noteId:String,
    ):Response<String>


    @PATCH("notes/note")
    suspend fun updateNote(
        @Header("Authorization") token:String,
        @Query("userId") userId:String,
        @Query("noteId") noteId:String,
    ):ResponseFromServer

}