package com.example.vaxcareandroidassessment.presentation.book_list

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vaxcareandroidassessment.common.Resource
import com.example.vaxcareandroidassessment.domain.use_case.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {

    //state for book list screen
    private val _state = mutableStateOf(BookListState())
    val state: State<BookListState> = _state

    init {
        getBooks()
    }

    //function to get books from use case
    @VisibleForTesting
    fun getBooks() {
        getBooksUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = BookListState(books = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = BookListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = BookListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}