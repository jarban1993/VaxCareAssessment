package com.example.vaxcareandroidassessment.data.remote.dto

import com.example.vaxcareandroidassessment.domain.model.Book
import com.example.vaxcareandroidassessment.domain.model.BookDetail

data class BookDto(
    val author: String,
    val fee: Double,
    val id: Int,
    val lastEdited: String,
    val status: Status,
    val title: String
)

fun BookDto.toBook(): Book {
    return Book(
        id = id,
        author = author,
        status = status,
        title = title
    )
}

fun BookDto.toBookDetail(): BookDetail {
    return BookDetail(
        id = id,
        author = author,
        fee = fee,
        lastEdited = lastEdited,
        status = status,
        title = title
    )
}