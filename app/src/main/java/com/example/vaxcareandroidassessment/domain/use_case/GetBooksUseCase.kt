package com.example.vaxcareandroidassessment.domain.use_case

import com.example.vaxcareandroidassessment.common.Resource
import com.example.vaxcareandroidassessment.data.remote.dto.toBook
import com.example.vaxcareandroidassessment.domain.model.Book
import com.example.vaxcareandroidassessment.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val repository: BookRepository
) {
    //Use case to fetch data from repository for all books
    operator fun invoke(): Flow<Resource<List<Book>>> = flow {
        try {
            emit(Resource.Loading<List<Book>>())
            val books = repository.getBooks().map { it.toBook() }
            emit(Resource.Success<List<Book>>(books))
        } catch(e: HttpException){
            emit(Resource.Error<List<Book>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException){
            emit(Resource.Error<List<Book>>("Couldn't reach server. Check your internet connection."))
        }
    }
}