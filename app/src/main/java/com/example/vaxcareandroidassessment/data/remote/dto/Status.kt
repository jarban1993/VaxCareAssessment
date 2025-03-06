package com.example.vaxcareandroidassessment.data.remote.dto

data class Status(
    val displayText: String,
    val dueDate: String,
    val id: Int,
    val timeCheckedIn: String,
    val timeCheckedOut: String
)