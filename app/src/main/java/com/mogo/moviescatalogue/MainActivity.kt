package com.mogo.moviescatalogue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mogo.moviescatalogue.common.Constants.PARAM_MOVIE_ID
import com.mogo.moviescatalogue.common.theme.AppTheme
import com.mogo.moviescatalogue.moviedetail.MovieDetailScreen
import com.mogo.moviescatalogue.movielist.MovieListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MOVIE_LIST_SCREEN_IDENTIFIER.route
                    ) {
                        composable(
                            route = Screen.MOVIE_LIST_SCREEN_IDENTIFIER.route
                        ) {
                            MovieListScreen(navController)
                        }
                        composable(
                            route = Screen.MOVIE_DETAIL_SCREEN_IDENTIFIER.route + "/{$PARAM_MOVIE_ID}"
                        ) {
                            MovieDetailScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
