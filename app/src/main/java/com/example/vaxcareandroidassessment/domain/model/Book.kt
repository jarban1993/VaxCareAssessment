package com.example.vaxcareandroidassessment.domain.model

import com.example.vaxcareandroidassessment.data.remote.dto.Status

data class Book(
    val author: String,
    val id: Int,
    val status: Status,
    val title: String
)