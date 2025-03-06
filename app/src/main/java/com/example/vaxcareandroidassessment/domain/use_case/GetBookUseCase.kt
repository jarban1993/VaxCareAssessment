package com.example.vaxcareandroidassessment.domain.use_case

import com.example.vaxcareandroidassessment.common.Resource
import com.example.vaxcareandroidassessment.data.remote.dto.toBook
import com.example.vaxcareandroidassessment.data.remote.dto.toBookDetail
import com.example.vaxcareandroidassessment.domain.model.Book
import com.example.vaxcareandroidassessment.domain.model.BookDetail
import com.example.vaxcareandroidassessment.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetBookUseCase @Inject constructor(
    private val repository: BookRepository
) {
    operator fun invoke(bookId: String): Flow<Resource<BookDetail>> = flow {
        try {
            emit(Resource.Loading<BookDetail>())
            val book = repository.getBook(bookId).toBookDetail()
            emit(Resource.Success<BookDetail>(book))
        } catch(e: HttpException){
            emit(Resource.Error<BookDetail>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException){
            emit(Resource.Error<BookDetail>("Couldn't reach server. Check your internet connection."))
        }
    }
}