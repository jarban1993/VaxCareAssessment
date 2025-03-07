package com.example.vaxcareandroidassessment.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.vaxcareandroidassessment.data.remote.dto.Status
import com.example.vaxcareandroidassessment.domain.model.BookDetail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: com.example.vaxcareandroidassessment.data.util.JsonParser
) {
    @TypeConverter
    fun fromBooksJson(json: String): List<BookDetail> {
        return jsonParser.fromJson<ArrayList<BookDetail>>(
            json,
            object : TypeToken<ArrayList<BookDetail>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toBooksJson(books: List<BookDetail>): String {
        return jsonParser.toJson(
            books,
            object : TypeToken<ArrayList<BookDetail>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromStatusJson(status: Status?): String? {
        return Gson().toJson(status)
    }

    @TypeConverter
    fun toStatusJson(status: String?): Status? {
        return Gson().fromJson(status, Status::class.java)
    }
}