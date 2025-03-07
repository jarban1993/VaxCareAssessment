package com.example.vaxcareandroidassessment

import com.example.vaxcareandroidassessment.common.Resource
import com.example.vaxcareandroidassessment.data.remote.dto.Status
import com.example.vaxcareandroidassessment.domain.model.Book
import com.example.vaxcareandroidassessment.domain.repository.BookRepository
import com.example.vaxcareandroidassessment.domain.use_case.GetBooksUseCase
import com.example.vaxcareandroidassessment.presentation.book_list.BookListViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnit4::class)
class BookListViewModelTest {

    private val testStatus1 = Status("OnShelf", "", 1, "test", "test")
    private val bookTest1 = Book("Charles Dickens", 1, testStatus1, "Tale of Two Cities")
    private val bookTest2 = Book("Mark Twain", 2, testStatus1, "The Adventures of Tom Sawyer")
    private val bookTest3 = Book("Jane Austen", 3, testStatus1, "Pride and Prejudice")
    private val bookTest4 = Book("Leo Tolstoy", 4, testStatus1, "War and Peace")
    private val bookTest5 = Book("Virginia Woolf", 5, testStatus1, "Mrs Dalloway")
    private val bookTest6 = Book("F. Scott Fitzgerald", 6, testStatus1, "The Great Gatsby")

    private val getBooksUseCase = mockk<GetBooksUseCase>()

    private lateinit var SUT : BookListViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `book list viewmodel get books returned successfully`() = runTest{
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        val bookFlow: Flow<Resource<List<Book>>> = flowOf(Resource.Success(listOf(bookTest1, bookTest2, bookTest3, bookTest4, bookTest5, bookTest6)))
        coEvery { getBooksUseCase.invoke() } returns bookFlow

        SUT = BookListViewModel(getBooksUseCase)

        coVerify(exactly = 1) { getBooksUseCase.invoke() }
        assertNotNull(SUT.state.value.books)
        assert(SUT.state.value.books.size == 6)
    }

    @Test
    fun `book list viewmodel get books returns error`() = runTest{
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        val bookError: Flow<Resource<List<Book>>> = flowOf(Resource.Error("An unexpected error occurred"))
        coEvery { getBooksUseCase.invoke() } returns bookError

        SUT = BookListViewModel(getBooksUseCase)

        coVerify(exactly = 1) { getBooksUseCase.invoke() }
        assert(SUT.state.value.error == "An unexpected error occurred")
    }

    @Test
    fun `book list viewmodel get books loading`() = runTest{
        val testDispatcher = UnconfinedTestDispatcher(testScheduler)
        Dispatchers.setMain(testDispatcher)
        val bookError: Flow<Resource<List<Book>>> = flowOf(Resource.Loading())
        coEvery { getBooksUseCase.invoke() } returns bookError

        SUT = BookListViewModel(getBooksUseCase)

        coVerify(exactly = 1) { getBooksUseCase.invoke() }
        assert(SUT.state.value.isLoading)
    }
}