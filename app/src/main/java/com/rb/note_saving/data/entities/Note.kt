package com.rb.note_saving.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note (
    val posterId: String ,
    val title:String,
    val subTitle:String,
    val noteText:String,
    val color: String,
    val imageLink:String?,
    val webLink:String?,
    val timeStamp:Long,
    @PrimaryKey
    val id:String
)
class AddNotesException(message:String):Exception(message){

}
