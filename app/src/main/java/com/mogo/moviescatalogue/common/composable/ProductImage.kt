package com.llyods.retailapp.presentation.feature.common.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.mogo.moviescatalogue.common.UiConstants.IMAGE_ROUNDED_CORNER_RADIUS
import com.mogo.moviescatalogue.common.UiConstants.MOVIE_LIST_IMAGE_SIZE

@OptIn(ExperimentalCoilApi::class)
@Composable
fun RoundedImage(
    imageUrl: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    imageSize: Dp = MOVIE_LIST_IMAGE_SIZE,
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
            .size(imageSize)
            .clip(RoundedCornerShape(IMAGE_ROUNDED_CORNER_RADIUS))
            .background(Color.White),
        contentDescription = contentDescription,
    )
}