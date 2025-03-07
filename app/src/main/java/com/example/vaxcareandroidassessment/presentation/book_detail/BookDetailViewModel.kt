package com.example.vaxcareandroidassessment.presentation.book_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vaxcareandroidassessment.common.Constants
import com.example.vaxcareandroidassessment.common.Resource
import com.example.vaxcareandroidassessment.domain.use_case.GetBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val getBookUseCase: GetBookUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(BookDetailState())
    val state: State<BookDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.BOOK_ID)?.let { bookId ->
            getBook(bookId)
        }
    }


    private fun getBook(bookId: String) {
        getBookUseCase(bookId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = BookDetailState(book = result.data)
                }
                is Resource.Error -> {
                    _state.value = BookDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = BookDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}