package com.example.vaxcareandroidassessment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vaxcareandroidassessment.presentation.theme.VaxCareAndroidAssessmentTheme
import com.example.vaxcareandroidassessment.presentation.book_list.BookListScreen
import com.example.vaxcareandroidassessment.presentation.book_detail.BookDetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VaxCareAndroidAssessmentTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.BookListScreen.route
                    ) {
                        composable(
                            route = Screen.BookListScreen.route
                        ) {
                            BookListScreen(navController)
                        }
                        composable(
                            route = Screen.BookDetailScreen.route + "/{bookId}"
                        ) {
                            BookDetailScreen()
                        }
                    }
                }
            }
        }
    }
}