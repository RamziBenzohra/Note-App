package com.rb.note_saving.di

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import com.rb.note_saving.data.local.NotesDAO
import com.rb.note_saving.data.remote.api.Api
import com.rb.note_saving.data.authentication.AuthenticationRepository
import com.rb.note_saving.data.authentication.AuthenticationRepositoryImpl
import com.rb.note_saving.data.notes.NotesRepository
import com.rb.note_saving.data.notes.NotesRepositoryImpl
import com.rb.note_saving.domain.AuthenticationUseCases
import com.rb.note_saving.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create


@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModules {

    @Provides
    @ViewModelScoped
    fun provideApi() :Api{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @ViewModelScoped
    fun provideNotesRepository(dao: NotesDAO,api:Api) : NotesRepository {
        return NotesRepositoryImpl(dao,api)
    }


    @Provides
    @ViewModelScoped
    fun provideAuthenticationRepository(api:Api,preferences:SharedPreferences) : AuthenticationRepository {
        return AuthenticationRepositoryImpl(api,preferences)
    }

    @Provides
    @ViewModelScoped
    fun provideAuthenticationUseCases(authenticationRepository: AuthenticationRepository):AuthenticationUseCases{
        return AuthenticationUseCases(authenticationRepository)
    }

}