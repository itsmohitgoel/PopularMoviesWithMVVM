package com.mogo.moviescatalogue.moviedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
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
import com.mogo.moviescatalogue.R
import com.mogo.moviescatalogue.common.UiConstants
import com.mogo.moviescatalogue.common.UiConstants.PADDING_LARGE
import com.mogo.moviescatalogue.common.UiConstants.PADDING_MEDIUM
import com.mogo.moviescatalogue.common.UiConstants.PADDING_SMALL
import com.mogo.moviescatalogue.common.composable.MovieTextLabel
import com.mogo.moviescatalogue.common.composable.PosterImage
import com.mogo.moviescatalogue.common.composable.TopToolBar
import com.mogo.moviescatalogue.common.model.MovieItem
import com.mogo.moviescatalogue.common.theme.TealDetailHeader
import com.mogo.moviescatalogue.moviedetail.viewmodel.MovieDetailViewModel

@Composable
fun MovieDetailScreen(
    navController: NavController,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val state: State<MovieDetailState> = viewModel.state.collectAsState()
    val movieDetail: MovieItem? = state.value.movieItem

    LaunchedEffect(Unit, block = {
        viewModel.getMovieDetail()  //TODO" remove hardcoded id
    })

    Column(modifier = Modifier.fillMaxWidth()) {
        TopToolBar(Pair(true, "Popular Movies"), navController)
        Box(
            modifier = Modifier
                .background(TealDetailHeader)
                .fillMaxWidth()
                .padding(16.dp, 16.dp, 8.dp, 24.dp)
                .weight(0.2f)
        ) {
            Text(
                text = movieDetail?.title ?: "N/A",
                modifier = Modifier
                    .align(Alignment.Center),
                style = TextStyle(
                    fontSize = 40.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Normal
                )

            )

        }
        Box(
            modifier = Modifier
                .padding(16.dp, 16.dp, 8.dp, 24.dp)
                .weight(0.3f)

        ) {
            Row {
                PosterImage(
                    imageUrl = movieDetail?.imageUrl ?: "",
                    contentDescription = "",
                    placeHolderImageResource = R.drawable.ic_launcher_background,
                    modifier = Modifier.padding(end = 36.dp)
                )
                Column {
                    // Year
                    Row(
                        modifier = Modifier.padding(
                            top = PADDING_MEDIUM,
                            bottom = PADDING_SMALL
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Release date Symbol"
                        )
                        MovieTextLabel(
                            title = movieDetail?.releaseDate ?: "",
                            maxLines = UiConstants.MAX_LINE_DOUBLE,
                            modifier = Modifier.padding(start = PADDING_MEDIUM)
                        )
                    }
                    Row(
                        modifier = Modifier.padding(
                            top = PADDING_MEDIUM,
                            bottom = PADDING_SMALL
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "IMDB rating Symbol"
                        )
                        MovieTextLabel(
                            title = "${movieDetail?.rating?.toInt() ?: 5} /10",
                            maxLines = UiConstants.MAX_LINE_DOUBLE,
                            modifier = Modifier.padding(start = PADDING_MEDIUM)
                        )
                    }
                    Row(
                        modifier = Modifier.padding(
                            top = PADDING_MEDIUM,
                            bottom = PADDING_SMALL
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ThumbUp,
                            contentDescription = "Like Symbol"
                        )
                        MovieTextLabel(
                            title = "${movieDetail?.popularity?.toInt() ?: 100}",
                            maxLines = UiConstants.MAX_LINE_DOUBLE,
                            modifier = Modifier.padding(start = PADDING_MEDIUM)
                        )
                    }
                    Row(
                        modifier = Modifier.padding(
                            top = PADDING_MEDIUM,
                            bottom = PADDING_SMALL
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = "Release date Symbol"
                        )
                        MovieTextLabel(
                            title = movieDetail?.tagline ?: "",
                            maxLines = UiConstants.MAX_LINE_DOUBLE,
                            modifier = Modifier.padding(start = PADDING_MEDIUM)
                        )
                    }
                }
            }

        }

        MovieTextLabel(
            title = movieDetail?.summary ?: "",
            maxLines = UiConstants.MAX_LINE_EIGHT,
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = PADDING_LARGE, top = PADDING_MEDIUM)
                .weight(0.5f)

        )
    }
}