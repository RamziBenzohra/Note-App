package com.rb.note_saving.di

import com.rb.note_saving.data.remote.api.NotesApi
import com.rb.note_saving.domain.Repository
import com.rb.note_saving.domain.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Modules {

    @Provides
    @Singleton
    fun provideApi() :NotesApi{
        return Retrofit.Builder()
            .baseUrl("http://localhost:8080/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideRepository(api:NotesApi) :Repository{
        return RepositoryImpl(api = api)
    }






}