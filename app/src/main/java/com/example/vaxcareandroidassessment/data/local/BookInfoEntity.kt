package com.example.vaxcareandroidassessment.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.vaxcareandroidassessment.data.remote.dto.BookDto
import com.example.vaxcareandroidassessment.data.remote.dto.Status

@Entity
data class BookInfoEntity(
    val author: String,
    val fee: Double,
    @PrimaryKey val id: Int,
    val lastEdited: String,
    val status: Status,
    val title: String
) {
    fun toBookInfo(): BookDto {
        return BookDto(
            id = id,
            author = author,
            fee = fee,
            lastEdited = lastEdited,
            status = status,
            title = title
        )
    }
}
