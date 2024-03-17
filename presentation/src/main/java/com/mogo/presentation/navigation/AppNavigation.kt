package com.mogo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mogo.presentation.moviedetail.MovieDetailScreen
import com.mogo.presentation.movielist.MovieListScreen
import com.mogo.presentation.navigation.NavigationParams.MOVIE_ID

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreensRoute.HOME_SCREEN.route) {
        composable(route = ScreensRoute.HOME_SCREEN.route,
            content = { MovieListScreen(navController)}) //TODO: Avoid passing navController

        composable(route = ScreensRoute.DETAIL_SCREEN.route,
            arguments = listOf(
                navArgument(name = MOVIE_ID , builder = {type = NavType.IntType})),
            content = {backStackEntry ->
                MovieDetailScreen(
                    navController = navController,
                    movieId = requireNotNull( backStackEntry.arguments?.getInt(MOVIE_ID)?: 0))})
    }
}