package com.mogo.moviescatalogue.movielist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mogo.moviescatalogue.Screen
import com.mogo.moviescatalogue.common.composable.TopToolBar
import com.mogo.moviescatalogue.movielist.component.MovieListItem
import com.mogo.moviescatalogue.movielist.viewmodel.MovieListViewModel

@Composable
fun MovieListScreen(
    navController: NavController,
    viewModel: MovieListViewModel = hiltViewModel()
) {
    val state: State<MovieListState> = viewModel.state.collectAsState()

    LaunchedEffect(Unit, block =
    {
        viewModel.getMoviesList()
    }
    )
    Column(modifier = Modifier.fillMaxWidth()) {
        TopToolBar(Pair(false, "Popular Movies"), navController)
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.value.movieList) { movieItem ->
                    MovieListItem(
                        movieItem = movieItem,
                        onClick = {
                            navController.navigate(Screen.MOVIE_DETAIL_SCREEN_IDENTIFIER.route + "/${movieItem.movieId}")
                        }
                    )
                }
            }
            if (state.value.error.isNotBlank()) {
                Text(
                    text = state.value.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if (state.value.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }

}