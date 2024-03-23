package com.mogo.presentation.movielist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mogo.presentation.common.composable.ErrorComponent
import com.mogo.presentation.common.composable.TopToolBar
import com.mogo.presentation.common.model.MovieItem
import com.mogo.presentation.movielist.component.MovieListItem
import com.mogo.presentation.movielist.viewmodel.MovieListViewModel
import com.mogo.presentation.navigation.ScreensRoute
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun MovieListScreen(
    navController: NavController
) {
    val viewModel: MovieListViewModel = hiltViewModel()
    val stateValue: MovieListViewState = viewModel.viewStateFlow.collectAsState().value

    var shouldCallLandingApi by rememberSaveable {
        mutableStateOf(true)
    }

    if (shouldCallLandingApi) {
        LaunchedEffect(Unit) {
            viewModel.submitAction(MovieListAction.LoadMovieList)
            shouldCallLandingApi = false
        }
    }

    LaunchedEffect(Unit) {
        viewModel.oneTimeEventChannelFlow
            .onEach {oneTimeEvent ->
                when (oneTimeEvent) {
                    is MovieListOneTimeEvent.NavigateToDetailScreen -> {
                        navController.navigate(
                            ScreensRoute.DETAIL_SCREEN.route + "/${oneTimeEvent.movieId}"
                        )
                    }
                }

            }.collect()

    }
    Column(modifier = Modifier.fillMaxWidth()) {
        TopToolBar(Pair(false, "Popular Movies"), navController)
        Box(modifier = Modifier.fillMaxSize()) {
            when {
                stateValue.loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                stateValue.error.isNotBlank() -> {
                    ErrorComponent(errorMessage = stateValue.error)
                }

                !stateValue.movies.isNullOrEmpty() -> {
                    MovieList(movies = stateValue.movies, onItemClick = { movieItem: MovieItem ->
                        viewModel.submitAction(
                            MovieListAction.MovieListItemClick(movieItem.movieId)
                        )
                    })
                }
            }
        }
    }
}

@Composable
fun MovieList(movies: List<MovieItem>, onItemClick: (MovieItem) -> Unit) {
    LazyColumn(state = rememberLazyListState(),
        modifier = Modifier.background(
            brush = Brush.linearGradient(
                colors = listOf(MaterialTheme.colors.primary,
                    MaterialTheme.colors.secondary
                    )
            )
        )) {
        items(
            items = movies,
            itemContent = { item ->
                MovieListItem(
                    movieItem = item,
                    onClick = { movieItem -> onItemClick(movieItem) })
            }
        )
    }
}
