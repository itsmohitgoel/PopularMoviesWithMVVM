package com.mogo.presentation.movielist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mogo.presentation.common.composable.TopToolBar
import com.mogo.presentation.common.model.MovieItem
import com.mogo.presentation.movielist.component.MovieListItem
import com.mogo.presentation.navigation.ScreensRoute
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun MovieListScreen(
    navController: NavController
) {
    val viewModel: MovieListViewModel = hiltViewModel()
    val stateValue: MovieListState = viewModel.viewStateFlow.collectAsState().value

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
                    Text(
                        text = stateValue.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }

                !stateValue.movies.isNullOrEmpty() -> {
                    MovieList(movies = stateValue.movies) { movieItem: MovieItem ->
                        viewModel.submitAction(
                            MovieListAction.MovieListItemClick(movieItem.movieId)
                        )
                    }
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
//    val state: State<MovieListOldState> = viewModel.state.collectAsState()
//
//    LaunchedEffect(Unit, block =
//    {
//        viewModel.getMoviesList()
//    }
//    )
//    Column(modifier = Modifier.fillMaxWidth()) {
//        TopToolBar(Pair(false, "Popular Movies"), navController)
//        Box(modifier = Modifier.fillMaxSize()) {
//            LazyColumn(modifier = Modifier.fillMaxSize()) {
//                items(state.value.movies) { movieItem ->
//                    MovieListItem(
//                        movieItem = movieItem,
//                        onClick = {
//                            navController.navigate(Screen.MOVIE_DETAIL_SCREEN_IDENTIFIER.route + "/${movieItem.movieId}")
//                        }
//                    )
//                }
//            }
//            if (state.value.error.isNotBlank()) {
//                Text(
//                    text = state.value.error,
//                    color = MaterialTheme.colors.error,
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 20.dp)
//                        .align(Alignment.Center)
//                )
//            }
//            if (state.value.isLoading) {
//                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//            }
//        }
//    }

