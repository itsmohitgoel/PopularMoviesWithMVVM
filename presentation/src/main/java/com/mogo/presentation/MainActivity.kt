package com.mogo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.mogo.presentation.common.theme.AppTheme
import com.mogo.presentation.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    AppNavigation()
//                    val navController = rememberNavController()
//                    NavHost(navController = navController, startDestination = HOME_SCREEN.routeIdentifier) {
//                        composable(route = HOME_SCREEN.routeIdentifier, content = {
//                            MovieListScreen(navController)
//                        })
//                        composable(route = DETAIL_SCREEN.routeIdentifier + "/{$PARAM_MOVIE_ID}", content = {
//                                MovieDetailScreen(navController)
//                            })
//                    }
                }
            }
        }
    }
}
