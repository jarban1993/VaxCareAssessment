package com.example.vaxcareandroidassessment.data.remote

import com.example.vaxcareandroidassessment.data.remote.dto.BookDto
import retrofit2.http.GET

interface BookApi {

    @GET("android-test-vaxcare/27bd7ab7d0381f867723225145694e93/raw/c530190f575aaac1ab8d5c416b2da9553be422fe/local-database2.json")
    suspend fun getBooks(): List<BookDto>
}