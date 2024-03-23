package com.mogo.presentation.movielist.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.mogo.presentation.R
import com.mogo.presentation.common.FontSize.TITLE
import com.mogo.presentation.common.Dimensions.MAX_LINE_DOUBLE
import com.mogo.presentation.common.Dimensions.PADDING_MEDIUM
import com.mogo.presentation.common.Dimensions.PADDING_SMALL
import com.mogo.presentation.common.composable.MovieTextLabel
import com.mogo.presentation.common.composable.RoundedImage
import com.mogo.presentation.common.model.MovieItem

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
        Column {
            //title
            MovieTextLabel(
                title = movieItem.title,
                fontWeight = FontWeight.Bold,
                fontSize = TITLE,
                maxLines = MAX_LINE_DOUBLE,
                modifier = Modifier.padding(start = PADDING_MEDIUM, top = PADDING_MEDIUM)
            )
            MovieTextLabel(
                title = "${movieItem.rating.toInt()}",
                maxLines = MAX_LINE_DOUBLE,
                modifier = Modifier.padding(start = PADDING_MEDIUM, top = PADDING_SMALL)
            )
        }
    }
}