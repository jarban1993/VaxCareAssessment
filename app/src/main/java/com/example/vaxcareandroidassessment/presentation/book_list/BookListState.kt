package com.example.vaxcareandroidassessment.presentation.book_list

import com.example.vaxcareandroidassessment.domain.model.Book

data class BookListState(
    val isLoading: Boolean = false,
    val books: List<Book> = emptyList(),
    val error: String = ""
)