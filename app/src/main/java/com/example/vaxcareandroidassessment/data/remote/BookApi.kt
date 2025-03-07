package com.example.vaxcareandroidassessment.data.remote

import com.example.vaxcareandroidassessment.data.remote.dto.BookDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface BookApi {

    @GET("android-test-vaxcare/27bd7ab7d0381f867723225145694e93/raw/c530190f575aaac1ab8d5c416b2da9553be422fe/local-database2.json")
    suspend fun getBooks(): List<BookDto>

    companion object {
        fun create(baseUrl: String): BookApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                // add the JSON dependency so we can handle json APIs
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            // here we pass a reference to our API interface
            // and get back a concrete instance
            return retrofit.create(BookApi::class.java)
        }
    }
}