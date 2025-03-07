package com.example.vaxcareandroidassessment.data.repository

import com.example.vaxcareandroidassessment.data.local.BookInfoDao
import com.example.vaxcareandroidassessment.data.remote.BookApi
import com.example.vaxcareandroidassessment.data.remote.dto.BookDto
import com.example.vaxcareandroidassessment.data.remote.dto.toBookEntity
import com.example.vaxcareandroidassessment.domain.repository.BookRepository
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val api: BookApi,
    private val dao: BookInfoDao
) : BookRepository {

    override suspend fun getBooks(): List<BookDto> {
        //Check if data is in database
        val localBooks = dao.getAllBooksInfo()
        if (localBooks.isNotEmpty()) {
            return localBooks.map { it.toBookInfo() }
        } else {
            //Fetch data from api
            val remoteBooks = api.getBooks()
            remoteBooks.forEach { dao.insertBookInfo(it.toBookEntity()) }
            return remoteBooks
        }
    }

    override suspend fun getBook(bookId: String): BookDto {
        //Check if data is in database
        val localBook = dao.getBookInfo(bookId.toInt())
        if (localBook != null) {
            return localBook.toBookInfo()
        } else {
            //Fetch data from api
            val remoteBook = api.getBooks().get(bookId.toInt())
            dao.insertBookInfo(remoteBook.toBookEntity())
            return remoteBook
        }
    }

}