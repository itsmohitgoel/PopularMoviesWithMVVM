package com.mogo.moviescatalogue._1_movielist.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.llyods.retailapp.presentation.feature.common.composable.MovieTextLabel
import com.llyods.retailapp.presentation.feature.common.composable.RoundedImage
import com.mogo.moviescatalogue.R
import com.mogo.moviescatalogue.common.FontSize.TITLE
import com.mogo.moviescatalogue.common.UiConstants.MAX_LINE_DOUBLE
import com.mogo.moviescatalogue.common.UiConstants.PADDING_MEDIUM
import com.mogo.moviescatalogue.common.model.MovieItem

@Composable
fun MovieListItem(movieItem: MovieItem, onClick: (MovieItem) -> Unit) {
    Row(
        modifier = Modifier
            .padding(PADDING_MEDIUM)
            .fillMaxWidth()
            .clickable {
                onClick(movieItem)
            },
    ) {
        RoundedImage(
            imageUrl = movieItem.imageUrl,
            contentDescription = movieItem.title,
            placeHolderImageResource = R.drawable.ic_launcher_background
        )
        //movie title
        Column {
            //title
            MovieTextLabel(
                title = movieItem.title + "39393939",
                fontWeight = FontWeight.Bold,
                fontSize = TITLE,
                modifier = Modifier.padding(start = PADDING_MEDIUM)
            )
            MovieTextLabel(
                title = movieItem.rating.toString(),
                maxLines = MAX_LINE_DOUBLE,
                modifier = Modifier.padding(start = PADDING_MEDIUM)
            )
        }
    }
}

@Preview
@Composable
fun MovieItemPreview(){
    MovieListItem(movieItem = MovieItem(
        imageUrl = "https://image.tmdb.org/t/p/w500//hu40Uxp9WtpL34jv3zyWLb5zEVY.jpg"
    )
    ) {

    }
}
