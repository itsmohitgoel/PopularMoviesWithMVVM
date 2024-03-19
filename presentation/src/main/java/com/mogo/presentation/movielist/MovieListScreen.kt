package com.mogo.presentation.movielist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

    LaunchedEffect(Unit) {
        viewModel.submitAction(MovieListAction.LoadMovieList)
    }

    LaunchedEffect(Unit) {
        viewModel.oneTimeEventChannelFlow
            .onEach {oneTimeEvent ->
                when (oneTimeEvent) {
                    is MovieListOneTimeEvent.NavigateToDetailScreen -> {
                        navController.navigate(
                            ScreensRoute.DETAIL_SCREEN.route + "/${oneTimeEvent.movieId}"
                        ) //TODO: keep navController in one place i.e. graph
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
    LazyColumn(state = rememberLazyListState()) {
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
