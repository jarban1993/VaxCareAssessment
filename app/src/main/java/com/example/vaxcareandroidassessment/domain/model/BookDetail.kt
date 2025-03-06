package com.example.vaxcareandroidassessment.domain.model

import com.example.vaxcareandroidassessment.data.remote.dto.Status

data class BookDetail(
    val author: String,
    val fee: Double,
    val id: Int,
    val lastEdited: String,
    val status: Status,
    val title: String
)