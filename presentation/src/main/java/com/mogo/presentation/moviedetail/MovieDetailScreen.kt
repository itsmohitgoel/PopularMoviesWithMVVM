package com.mogo.presentation.moviedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mogo.presentation.R
import com.mogo.presentation.common.Dimensions
import com.mogo.presentation.common.Dimensions.MAX_LINE_DOUBLE
import com.mogo.presentation.common.Dimensions.PADDING_EXTRA_LARGE
import com.mogo.presentation.common.Dimensions.PADDING_LARGE
import com.mogo.presentation.common.Dimensions.PADDING_MEDIUM
import com.mogo.presentation.common.Dimensions.PADDING_SMALL
import com.mogo.presentation.common.composable.ErrorComponent
import com.mogo.presentation.common.composable.MovieTextLabel
import com.mogo.presentation.common.composable.PosterImage
import com.mogo.presentation.common.composable.TopToolBar
import com.mogo.presentation.common.model.MovieItem
import com.mogo.presentation.common.theme.TealDetailHeader
import com.mogo.presentation.common.theme.Typography
import com.mogo.presentation.moviedetail.viewmodel.MovieDetailViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    movieId: Int
) {
    val viewModel: MovieDetailViewModel = hiltViewModel()
    val stateValue: MovieDetailViewState = viewModel.viewStateFlow.collectAsState().value

    var shouldCallLandingApi by rememberSaveable {
        mutableStateOf(true)
    }

    if (shouldCallLandingApi) {
        LaunchedEffect(Unit) {
            viewModel.submitAction(MovieDetailAction.LoadMovieDetail(movieId = movieId))
        }
        shouldCallLandingApi = false
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

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopToolBar(Pair(true, "Popular Movies"), navController)
        when {
            stateValue.loading -> {
                CircularProgressIndicator(modifier = modifier.align(alignment = Alignment.CenterHorizontally))
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
                .weight(0.2f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = movieItem.title,
                textAlign = TextAlign.Center,
                maxLines = MAX_LINE_DOUBLE,
                color = Color.White,
                modifier = modifier
                    .padding(PADDING_LARGE),
                style = Typography.h1
            )

        }
        Box(
            modifier = modifier
                .padding(
                    PADDING_LARGE,
                    PADDING_LARGE,
                    PADDING_MEDIUM,
                    PADDING_EXTRA_LARGE
                )
                .weight(0.3f)
        ) {
            Row(
                modifier = modifier
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.LightGray,
                                Color.Yellow,
                                Color.Magenta
                            )
                        )
                    )

            ) {
                PosterImage(
                    imageUrl = movieItem.imageUrl,
                    contentDescription = "",
                    placeHolderImageResource = R.drawable.ic_launcher_background
                )
                Spacer(modifier = Modifier.padding(PADDING_MEDIUM))
                Column(modifier = modifier.fillMaxWidth(1f)) {
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
                            maxLines = MAX_LINE_DOUBLE,
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
                            maxLines = MAX_LINE_DOUBLE,
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
                            maxLines = MAX_LINE_DOUBLE,
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
                            maxLines = MAX_LINE_DOUBLE,
                            modifier = modifier.padding(start = PADDING_MEDIUM)
                        )
                    }
                }
            }

        }

        MovieTextLabel(
            title = movieItem?.summary ?: "",
            maxLines = Dimensions.MAX_LINE_EIGHT,
            modifier = modifier
                .fillMaxHeight()
                .padding(start = PADDING_LARGE, top = PADDING_SMALL)
                .weight(0.5f)
                )
    }
}