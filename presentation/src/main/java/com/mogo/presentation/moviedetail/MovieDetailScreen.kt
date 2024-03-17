package com.mogo.presentation.moviedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mogo.presentation.R
import com.mogo.presentation.common.UiConstants
import com.mogo.presentation.common.UiConstants.PADDING_LARGE
import com.mogo.presentation.common.UiConstants.PADDING_MEDIUM
import com.mogo.presentation.common.UiConstants.PADDING_SMALL
import com.mogo.presentation.common.composable.ErrorComponent
import com.mogo.presentation.common.composable.MovieTextLabel
import com.mogo.presentation.common.composable.PosterImage
import com.mogo.presentation.common.composable.TopToolBar
import com.mogo.presentation.common.model.MovieItem
import com.mogo.presentation.common.theme.TealDetailHeader
import com.mogo.presentation.moviedetail.viewmodel.MovieDetailViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun  MovieDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    movieId: Int
) {
    val viewModel: MovieDetailViewModel = hiltViewModel()
    val stateValue: MovieDetailState = viewModel.viewStateFlow.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.submitAction(MovieDetailAction.LoadMovieDetail(movieId = movieId))
    }

    LaunchedEffect(Unit) {
        viewModel.oneTimeEventChanneFlow
            .onEach { oneTimeEvent ->
                when (oneTimeEvent) {
                    else -> {}
                }
            }
            .collect()
    }
//    val state: State<MovieDetailState> = viewModel.state.collectAsState()
//    val movieDetail: MovieItem? = state.value.movieItem
//
//    LaunchedEffect(Unit, block = {
//        viewModel.getMovieDetail()  //TODO" remove hardcoded id
//    })
//
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        TopToolBar(Pair(true, "Popular Movies"), navController)
        when {
            stateValue.loading -> {
                CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
            }
            stateValue.error.isNotBlank() -> ErrorComponent(errorMessage = stateValue.error)
            stateValue.movie.movieId > 0 -> {
                MovieDetailUI(movieItem = stateValue.movie, modifier)
            }
        }
    }
}

@Composable
fun MovieDetailUI(movieItem: MovieItem, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Box(
            modifier = modifier
                .background(TealDetailHeader)
                .fillMaxWidth()
                .weight(0.2f)
        ) {
            Text(
                text = movieItem.title,
                modifier = modifier
                    .align(Alignment.Center),
                style = TextStyle(
                    fontSize = 40.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Normal
                )

            )

        }
        Box(
            modifier = modifier
                .padding(16.dp, 16.dp, 8.dp, 24.dp)
                .weight(0.3f)
        ) {
            Row {
                PosterImage(
                    imageUrl = movieItem.imageUrl,
                    contentDescription = "",
                    placeHolderImageResource = R.drawable.ic_launcher_background,
                    modifier = modifier.padding(end = 36.dp)
                )
                Column {
                    // Year
                    Row(
                        modifier = modifier.padding(
                            top = PADDING_MEDIUM,
                            bottom = PADDING_SMALL
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Release date Symbol"
                        )
                        MovieTextLabel(
                            title = movieItem.releaseDate,
                            maxLines = UiConstants.MAX_LINE_DOUBLE,
                            modifier = modifier.padding(start = PADDING_MEDIUM)
                        )
                    }
                    Row(
                        modifier = modifier.padding(
                            top = PADDING_MEDIUM,
                            bottom = PADDING_SMALL
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "IMDB rating Symbol"
                        )
                        MovieTextLabel(
                            title = "${movieItem.rating.toInt() ?: 5} /10",
                            maxLines = UiConstants.MAX_LINE_DOUBLE,
                            modifier = modifier.padding(start = PADDING_MEDIUM)
                        )
                    }
                    Row(
                        modifier = modifier.padding(
                            top = PADDING_MEDIUM,
                            bottom = PADDING_SMALL
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ThumbUp,
                            contentDescription = "Like Symbol"
                        )
                        MovieTextLabel(
                            title = "${movieItem?.popularity?.toInt() ?: 100}",
                            maxLines = UiConstants.MAX_LINE_DOUBLE,
                            modifier = modifier.padding(start = PADDING_MEDIUM)
                        )
                    }
                    Row(
                        modifier = modifier.padding(
                            top = PADDING_MEDIUM,
                            bottom = PADDING_SMALL
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = "Release date Symbol"
                        )
                        MovieTextLabel(
                            title = movieItem?.tagline ?: "",
                            maxLines = UiConstants.MAX_LINE_DOUBLE,
                            modifier = modifier.padding(start = PADDING_MEDIUM)
                        )
                    }
                }
            }

        }

        MovieTextLabel(
            title = movieItem?.summary ?: "",
            maxLines = UiConstants.MAX_LINE_EIGHT,
            modifier = modifier
                .fillMaxHeight()
                .padding(start = PADDING_LARGE, top = PADDING_MEDIUM)
                .weight(0.5f)

        )
    }
}