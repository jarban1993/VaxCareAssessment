package com.example.vaxcareandroidassessment.domain.repository

import com.example.vaxcareandroidassessment.data.remote.dto.BookDto

interface BookRepository {

    //Repository interface to fetch data from api or database
    suspend fun getBooks(): List<BookDto>

    suspend fun getBook(bookId: String): BookDto
}