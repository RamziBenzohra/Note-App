package com.rb.note_saving.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room

import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

import com.rb.note_saving.data.local.NotesDatabase

import com.rb.note_saving.utils.Constants.DATABASE_NAME
import com.rb.note_saving.utils.Constants.SHARED_PREFERENCES_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext

import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context:Context)=Room.databaseBuilder(
        context,
        NotesDatabase::class.java, DATABASE_NAME
    ).build()


    @Provides
    @Singleton
    fun provideDAO(db:NotesDatabase)=db.notesDao()

    @Provides
    @Singleton
    fun provideEncryptedSharedPreferences(@ApplicationContext context: Context) : SharedPreferences {
        return EncryptedSharedPreferences.create(
            SHARED_PREFERENCES_NAME,
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}