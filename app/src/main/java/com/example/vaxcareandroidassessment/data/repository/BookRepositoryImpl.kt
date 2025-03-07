package com.example.vaxcareandroidassessment.data.repository

import com.example.vaxcareandroidassessment.data.local.BookInfoDao
import com.example.vaxcareandroidassessment.data.remote.BookApi
import com.example.vaxcareandroidassessment.data.remote.dto.BookDto
import com.example.vaxcareandroidassessment.data.remote.dto.toBookDetail
import com.example.vaxcareandroidassessment.data.remote.dto.toBookEntity
import com.example.vaxcareandroidassessment.domain.repository.BookRepository
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: BookApi,
    private val dao: BookInfoDao
) : BookRepository {

    override suspend fun getBooks(): List<BookDto> {
        val localBooks = dao.getAllBooksInfo()
        if (localBooks.isNotEmpty()) {
            return localBooks.map { it.toBookInfo() }
        } else {
            val remoteBooks = api.getBooks()
            remoteBooks.forEach { dao.insertBookInfo(it.toBookEntity()) }
            return remoteBooks
        }
    }

    override suspend fun getBook(bookId: String): BookDto {
        val localBook = dao.getBookInfo(bookId.toInt())
        if (localBook != null) {
            return localBook.toBookInfo()
        } else {
            val remoteBook = api.getBooks().get(bookId.toInt())
            dao.insertBookInfo(remoteBook.toBookEntity())
            return remoteBook
        }
    }

}