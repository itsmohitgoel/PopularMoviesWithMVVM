package com.llyods.retailapp.presentation.feature.common.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.mogo.moviescatalogue.common.UiConstants.MOVIE_DETAIL_IMAGE_HEIGHT
import com.mogo.moviescatalogue.common.UiConstants.MOVIE_DETAIL_IMAGE_WIDTH

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PosterImage(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    @DrawableRes placeHolderImageResource: Int
) {
    Image(
        painter = rememberImagePainter(
            data = imageUrl,
            builder = {
                placeholder(placeHolderImageResource)
            },
        ),
        contentScale = ContentScale.Crop,
        modifier = modifier
            .width(MOVIE_DETAIL_IMAGE_WIDTH)
            .height(MOVIE_DETAIL_IMAGE_HEIGHT)
//            .clip(RoundedCornerShape(IMAGE_ROUNDED_CORNER_RADIUS))
            .background(Color.White),
        contentDescription = contentDescription,
    )
}