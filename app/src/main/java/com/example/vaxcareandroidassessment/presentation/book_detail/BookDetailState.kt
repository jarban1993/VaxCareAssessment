package com.example.vaxcareandroidassessment.presentation.book_detail

import com.example.vaxcareandroidassessment.domain.model.BookDetail

data class BookDetailState (
    val isLoading: Boolean = false,
    val book: BookDetail? = null,
    val error: String = ""
)
