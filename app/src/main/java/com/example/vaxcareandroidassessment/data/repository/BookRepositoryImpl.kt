package com.example.vaxcareandroidassessment.data.repository

import android.util.Log
import com.example.vaxcareandroidassessment.data.local.BookInfoDao
import com.example.vaxcareandroidassessment.data.remote.BookApi
import com.example.vaxcareandroidassessment.data.remote.dto.BookDto
import com.example.vaxcareandroidassessment.domain.repository.BookRepository
import retrofit2.HttpException
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: BookApi,
) : BookRepository {

    override suspend fun getBooks(): List<BookDto> {
        return api.getBooks()
    }

    override suspend fun getBook(bookId: String): BookDto {
        return api.getBooks().get(bookId.toInt())
    }

}