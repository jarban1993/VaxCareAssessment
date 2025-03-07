package com.example.vaxcareandroidassessment.di

import android.app.Application
import androidx.room.Room
import com.example.vaxcareandroidassessment.common.Constants
import com.example.vaxcareandroidassessment.data.local.BookInfoDb
import com.example.vaxcareandroidassessment.data.local.Converters
import com.example.vaxcareandroidassessment.data.remote.BookApi
import com.example.vaxcareandroidassessment.data.repository.BookRepositoryImpl
import com.example.vaxcareandroidassessment.data.util.GsonParser
import com.example.vaxcareandroidassessment.domain.repository.BookRepository
import com.google.gson.Gson
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
    //creates a single instance of the api
    fun provideBookApi(): BookApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BookApi::class.java)
    }

    @Provides
    @Singleton
    //creates a single instance of the repository
    fun provideBookRepository(api: BookApi, db: BookInfoDb): BookRepository {
        return BookRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    //creates a single instance of the database
    fun provideBookInfoDatabase(app: Application): BookInfoDb {
        return Room.databaseBuilder(
            app, BookInfoDb::class.java, "book_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }

}