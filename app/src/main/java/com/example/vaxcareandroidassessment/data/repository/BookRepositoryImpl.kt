package com.example.vaxcareandroidassessment.data.repository

import com.example.vaxcareandroidassessment.data.remote.BookApi
import com.example.vaxcareandroidassessment.data.remote.dto.BookDto
import com.example.vaxcareandroidassessment.domain.repository.BookRepository
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: BookApi
) : BookRepository {

    override suspend fun getBooks(): List<BookDto> {
        return api.getBooks()
    }

    override suspend fun getBook(bookId: String): BookDto {
        TODO("Not yet implemented")
    }
}