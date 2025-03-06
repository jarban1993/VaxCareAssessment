package com.example.vaxcareandroidassessment.di

import com.example.vaxcareandroidassessment.common.Constants
import com.example.vaxcareandroidassessment.data.remote.BookApi
import com.example.vaxcareandroidassessment.data.repository.BookRepositoryImpl
import com.example.vaxcareandroidassessment.domain.repository.BookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBookApi(): BookApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBookRepository(api: BookApi): BookRepository {
        return BookRepositoryImpl(api)
    }
}