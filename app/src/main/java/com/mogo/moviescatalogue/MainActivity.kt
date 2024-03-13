package com.mogo.moviescatalogue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mogo.common.theme.AppTheme
import com.mogo.moviescatalogue._1_movielist.MovieListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screens.MovieListScreenIdentifier.route
                    ) {
                        composable(
                            route = Screens.MovieListScreenIdentifier.route
                        ) {
                            MovieListScreen(this@MainActivity, navController)
                        }
//                        composable(
//                            route = Screens.CoinDetailScreen.route + "/{coinId}"
//                        ) {
//                            CoinDetailScreen()
//                        }
                    }
                }
            }
        }
    }
}
