package com.example.vaxcareandroidassessment.presentation.book_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BookDetailScreen(
    viewModel: BookDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.book?.let { book ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = book.title,
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.weight(8f)
                        )
                        Text(
                            text = book.status.displayText,
                            color = if(book.status.id == 1) Color.Green else Color.Red,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(2f)
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Author: ${book.author}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Fee: $${book.fee}"
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = if(book.status.id == 1) "Time Checked In: ${book.status.timeCheckedIn}" else "Time Checked Out: ${book.status.timeCheckedOut}.\nDue Date: ${book.status.dueDate}" ,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "last edited: ${book.lastEdited}",
                        style = MaterialTheme.typography.labelSmall,
                    )
                }
            }
        }
        if(state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}