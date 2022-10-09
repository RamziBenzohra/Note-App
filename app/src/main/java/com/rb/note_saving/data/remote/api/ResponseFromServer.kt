package com.rb.note_saving.data.remote.api

import kotlinx.serialization.Serializable


@Serializable
data class ResponseFromServer(
    val noteId:String,
    val userToken:String
)
